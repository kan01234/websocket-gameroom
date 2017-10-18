package com.dotterbear.websocket.gameroom.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dotterbear.websocket.gameroom.model.AbstractGame;
import com.dotterbear.websocket.gameroom.model.Player;
import com.dotterbear.websocket.gameroom.repository.GameRepository;
import com.dotterbear.websocket.gameroom.repository.PlayerRepository;
import com.dotterbear.websocket.gameroom.utils.PlayerUtils;

@Service
public class PlayerService extends AbstractWebSocketService {

	Logger logger = LoggerFactory.getLogger(PlayerService.class);

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	GameRepository<AbstractGame> gameRepository;

	@Autowired
	GameService<AbstractGame> gameService;

	@Autowired
	PlayerUtils playerUtils;

	@Value(value = "${websocket.gameroom.config.numof.player}")
	int numOfPlayer;

	public String addPlayer(String sessionId, String name) {
		logger.info("addPlayer, sessionId: " + sessionId);
		Map<String, AbstractGame> waitingGameMap = gameRepository.getWaitingGameMap();
		AbstractGame abstractGame = null;
		abstractGame = waitingGameMap.values().stream().filter(g -> g.getJoinCount().getAndIncrement() <= numOfPlayer).findFirst().orElse(null);
		// create new game, if no available waiting game exists
		if (abstractGame == null) {
			abstractGame = gameService.newGame();
			waitingGameMap.put(abstractGame.getId(), abstractGame);
		}
		return addPlayer(sessionId, name, abstractGame);
	}

	public String addPlayer(String sessionId, String gameId, String name) {
		logger.info("addPlayer, sessionId: " + sessionId + ", gameId: " + gameId + ", name: " + name);
		AbstractGame abstractGame = gameRepository.getWaitingGame(gameId);
		if (abstractGame != null && abstractGame.getJoinCount().incrementAndGet() <= numOfPlayer)
			return addPlayer(sessionId, name, abstractGame);
		else
			sendTo("joined", sessionId, "error", true);
		return null;
	}

	String addPlayer(String sessionId, String name, AbstractGame abstractGame) {
		logger.info("addPlayer, sessionId: " + sessionId + ", game: " + abstractGame + ", name: " + name);
		if (abstractGame == null)
			return null;
		String gameId = null;
		int i = 0;
		Player[] players = abstractGame.getPlayers();

		for (; i < players.length; i++) {
			if (players[i] == null) {
				players[i] = new Player(name, sessionId);
				break;
			}
		}
		gameId = abstractGame.getId();
		playerRepository.addPlayer(sessionId, gameId);
		sendTo("joined", sessionId, new String[] { "error", "game-id", "index" }, new Object[] { false, gameId, i });
		return gameId;
	}

	public void removePlayer(String sessionId) {
		logger.info("removePlayer, sessionId: " + sessionId);
		Map<String, String> playerGameMap = playerRepository.getPlayerGameMap();
		if (!playerGameMap.containsKey(sessionId))
			return;
		String gameId = playerGameMap.get(sessionId);
		AbstractGame abstractGame = gameRepository.getWaitingGame(gameId);
		boolean isWaiting = true;
		if (abstractGame == null) {
			abstractGame = gameRepository.getPlayingGame(gameId);
			isWaiting = false;
		}
		playerRepository.removePlayer(sessionId);
		Player[] players = abstractGame.getPlayers();
		int i;
		for (i = 0; i < players.length; i++) {
			if (players[i] != null && players[i].getSessionId().equals(sessionId)) {
				players[i] = null;
				break;
			}
		}
		if (isWaiting) {
			if (abstractGame.getReadyCount().get() > abstractGame.getJoinCount().decrementAndGet())
				abstractGame.getReadyCount().decrementAndGet();
		} else {
			if (abstractGame.getReadyCount().decrementAndGet() == 1) {
				gameService.playerWin(gameId, playerUtils.getLastPlayerIndex(players));
				return;
			}
			gameService.playerLeaved(abstractGame, i);
		}
		send("player-list", abstractGame.getId(), "players", players);
	}

	public void ready(String sessionId, String gameId) {
		logger.info("ready, sessionId: " + sessionId + " , gam");
		AbstractGame abstractGame = gameRepository.getWaitingGame(gameId);
		if (abstractGame == null)
			return;
		send("player-list", gameId, "players", abstractGame.getPlayers());
		if (abstractGame.getReadyCount().incrementAndGet() == numOfPlayer)
			gameService.start(gameId);
	}

}
