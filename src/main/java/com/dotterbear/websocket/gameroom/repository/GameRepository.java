package com.dotterbear.websocket.gameroom.repository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dotterbear.websocket.gameroom.model.AbstractGame;

@Repository
public class GameRepository<T extends AbstractGame> {

	/**
	 * map to store waiting games
	 */
	// TODO move to redis ?
	@Autowired
	private Map<String, T> waitingGameMap;

	/**
	 * map to store playing games
	 */
	// TODO move to redis ?
	@Autowired
	private Map<String, T> playingGameMap;

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * get the playing game by gameId
	 * 
	 * @param gameId
	 *            gameId of that game
	 * @return game or null if the gameId not exists
	 */
	public T getPlayingGame(String gameId) {
		return playingGameMap.containsKey(gameId) ? playingGameMap.get(gameId) : null;
	}

	/**
	 * get the waiting game by gameId
	 * 
	 * @param gameId
	 *            gameId of that game
	 * @return game or null if the gameId not exists
	 */
	public T getWaitingGame(String gameId) {
		return waitingGameMap.containsKey(gameId) ? waitingGameMap.get(gameId) : null;
	}

	/**
	 * get the map store all waiting games
	 * 
	 * @return map store all waiting games
	 */
	public Map<String, T> getWaitingGameMap() {
		return waitingGameMap;
	}

	/**
	 * get the map store all playing games
	 * 
	 * @return map store all playing games
	 */
	public Map<String, T> getPlayingGameMap() {
		return playingGameMap;
	}

	/**
	 * remove the playing game, also update the player and game pair stored in
	 * playerRepository
	 * 
	 * @param gameId
	 *            gameId of playing game
	 * @return game object or null if the game not exists
	 */
	public T removePlayingGame(String gameId) {
		T t = playingGameMap.remove(gameId);
		if (t != null)
			playerRepository.removePlayer(Stream.of(t.getPlayers()).filter(p -> p != null).map(p -> p.getSessionId()).collect(Collectors.toList()));
		return t;
	}

	/**
	 * remove the waiting game in the map
	 * 
	 * @param gameId
	 *            gameId of the game
	 * @return game object or null if the game not exists
	 */
	public T removeWaitingGame(String gameId) {
		return waitingGameMap.remove(gameId);
	}

	/**
	 * take a game object and store it to playing game map
	 * 
	 * @param gameId
	 *            gameId of the game
	 * @param t
	 *            object extend abstractGame
	 * @return object added to playing game map
	 */
	public T addPlayingGame(String gameId, T t) {
		playingGameMap.put(gameId, t);
		return t;
	}

	/**
	 * take a game object and store it to waiting game map
	 * 
	 * @param gameId
	 *            gameId of the game
	 * @param t
	 *            object extend abstractGame
	 * @return object added to waiting game map
	 */
	public T addWaitingGame(String gameId, T t) {
		waitingGameMap.put(gameId, t);
		return t;
	}

}
