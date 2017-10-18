package com.dotterbear.websocket.gameroom.builder;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.dotterbear.websocket.gameroom.model.AbstractGame;
import com.dotterbear.websocket.gameroom.model.Player;

public abstract class AbstractGameBuilder<T extends AbstractGame> {

	@Value(value = "${websocket.gameroom.config.numof.player}")
	protected int numOfPlayer;

	public T build(T t) {
		t.setId(UUID.randomUUID().toString());
		t.setPlayers(new Player[numOfPlayer]);
		return t;
	}

}
