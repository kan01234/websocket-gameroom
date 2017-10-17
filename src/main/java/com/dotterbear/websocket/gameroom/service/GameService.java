package com.dotterbear.websocket.gameroom.service;

import org.springframework.stereotype.Service;

import com.dotterbear.websocket.gameroom.model.Game;

@Service
public interface GameService<T extends Game> {

	T newGame();

	void playerWin(String gameId, int lastPlayerIndex);

	void playerLeaved(T t, int i);

	void start(String gameId);

}
