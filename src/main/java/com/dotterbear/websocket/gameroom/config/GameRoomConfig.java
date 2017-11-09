package com.dotterbear.websocket.gameroom.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dotterbear.websocket.gameroom.model.AbstractGame;
import com.dotterbear.websocket.gameroom.utils.PlayerUtils;

@Configuration
public class GameRoomConfig {

	@Bean(name = "playerGameMap")
	public Map<String, String> getPlayerGameMap() {
		return new ConcurrentHashMap<>();
	}

	@Bean(name = "playingGameMap")
	public Map<String, AbstractGame> getPlayingGameMap() {
		return new ConcurrentHashMap<>();
	}

	@Bean(name = "waitingGameMap")
	public Map<String, AbstractGame> getWaitingGameMap() {
		return new ConcurrentHashMap<>();
	}

	@Bean
	public PlayerUtils getPlayerUtils() {
		return new PlayerUtils();
	}

}
