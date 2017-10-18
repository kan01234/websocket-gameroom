package com.dotterbear.websocket.gameroom.repository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotterbear.websocket.gameroom.model.AbstractGame;

@Repository
public class GameRepository<T extends AbstractGame> {

	// TODO move to redis ?
	@Autowired
	Map<String, T> waitingGameMap;

	// TODO move to redis ?
	@Autowired
	Map<String, T> playingGameMap;

	@Autowired
	PlayerRepository playerRepository;

	public T getPlayingGame(String gameId) {
		return playingGameMap.containsKey(gameId) ? playingGameMap.get(gameId) : null;
	}

	public T getWaitingGame(String gameId) {
		return waitingGameMap.containsKey(gameId) ? waitingGameMap.get(gameId) : null;
	}

	public Map<String, T> getWaitingGameMap() {
		return waitingGameMap;
	}

	public Map<String, T> getPlayingGameMap() {
		return playingGameMap;
	}

	public T removePlayingGame(String gameId) {
		T t = playingGameMap.remove(gameId);
		if (t != null)
			playerRepository.removePlayer(Stream.of(t.getPlayers()).map(p -> p.getSessionId()).collect(Collectors.toList()));
		return t;
	}

	public T removeWaitingGame(String gameId) {
		return waitingGameMap.remove(gameId);
	}

	public T addPlayingGame(String gameId, T t) {
		playingGameMap.put(gameId, t);
		return t;
	}

	public T addWaitingGame(String gameId, T t) {
		waitingGameMap.put(gameId, t);
		return t;
	}

}
