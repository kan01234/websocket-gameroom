package com.dotterbear.websocket.gameroom.builder;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.dotterbear.websocket.gameroom.model.Game;
import com.dotterbear.websocket.gameroom.model.Player;

public abstract class AbstractGameBuilder<T extends Game> {

	@Value(value = "${websocket.gameroom.config.numof.player}")
	protected int numOfPlayer;

	public T build(T game) {
		game.setId(UUID.randomUUID().toString());
		game.setPlayers(new Player[numOfPlayer]);
		return game;
	}

}
