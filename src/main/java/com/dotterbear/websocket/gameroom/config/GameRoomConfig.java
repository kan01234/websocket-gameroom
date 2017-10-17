package com.dotterbear.websocket.gameroom.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dotterbear.websocket.gameroom.model.Game;
import com.dotterbear.websocket.gameroom.utils.PlayerUtils;

@Configuration
public class GameRoomConfig {

	@Bean(name = "playerGameMap")
	public Map<String, String> getPlayerGameMap() {
		return new ConcurrentHashMap<String, String>();
	}

	@Bean(name = "playingGameMap")
	public Map<String, Game> getPlayingGameMap() {
		return new ConcurrentHashMap<String, Game>();
	}

	@Bean(name = "waitingGameMap")
	public Map<String, Game> getWaitingGameMap() {
		return new ConcurrentHashMap<String, Game>();
	}

	@Bean
	public PlayerUtils getPlayerUtils() {
		return new PlayerUtils();
	}

}
