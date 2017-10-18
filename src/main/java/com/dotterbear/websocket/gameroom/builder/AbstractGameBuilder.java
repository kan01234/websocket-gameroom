package com.dotterbear.websocket.gameroom.builder;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.dotterbear.websocket.gameroom.model.AbstractGame;
import com.dotterbear.websocket.gameroom.model.Player;

public abstract class AbstractGameBuilder<T extends AbstractGame> {

	/**
	 * max number of player of each room. Change by
	 * websocket.gameroom.config.numof.player properties.
	 */
	@Value(value = "${websocket.gameroom.config.numof.player}")
	protected int numOfPlayer;

	/**
	 * 
	 * @param t
	 *            object extends abstractGame
	 * @return object after initialize gameId, number of player
	 */
	public T build(T t) {
		t.setId(UUID.randomUUID().toString());
		t.setPlayers(new Player[numOfPlayer]);
		return t;
	}

}
