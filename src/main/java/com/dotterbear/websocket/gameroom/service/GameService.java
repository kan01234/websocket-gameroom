package com.dotterbear.websocket.gameroom.service;

import org.springframework.stereotype.Service;

import com.dotterbear.websocket.gameroom.builder.AbstractGameBuilder;
import com.dotterbear.websocket.gameroom.model.AbstractGame;

@Service
public interface GameService<T extends AbstractGame> {

	/**
	 * return the new object extends game. better use {@link AbstractGameBuilder}
	 * build method to init the gameId and number of players.
	 * 
	 * @return object extends game
	 */
	T newGame();

	/**
	 * handle a player has won, and the game is ended.
	 * 
	 * @param gameId
	 *            gameId of the game
	 * @param playerIndex
	 *            index of the player who won the game
	 */
	void playerWin(String gameId, int playerIndex);

	/**
	 * handle a player leaved the game
	 * 
	 * @param t
	 *            object extends the game
	 * @param i
	 *            index of the player who leaved the game
	 */
	void playerLeaved(T t, int i);

	/**
	 * handle the game is start
	 * 
	 * @param gameId
	 *            gameId of the game needed to start
	 */
	void start(String gameId);

}
