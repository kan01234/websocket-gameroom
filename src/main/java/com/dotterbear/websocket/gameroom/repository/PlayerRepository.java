package com.dotterbear.websocket.gameroom.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository {

	// TODO move to redis ?
	@Autowired
	Map<String, String> playerGameMap;

	public Map<String, String> getPlayerGameMap() {
		return playerGameMap;
	}

	public String removePlayer(String sessionId) {
		return playerGameMap.remove(sessionId);
	}

	public void removePlayer(List<String> sessionIds) {
		playerGameMap.keySet().removeAll(sessionIds);
	}

	public void addPlayer(String sessionId, String gameId) {
		playerGameMap.put(sessionId, gameId);
	}

}
