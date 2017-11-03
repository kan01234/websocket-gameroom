package com.dotterbear.websocket.gameroom.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGame {

	String id;
	protected Player[] players;
	Set<String> readyPlayers = new HashSet<String>();
	AtomicInteger joinCount = new AtomicInteger(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public AtomicInteger getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(AtomicInteger joinCount) {
		this.joinCount = joinCount;
	}

	public void addReadyPlayer(String sessionId) {
		readyPlayers.add(sessionId);
	}

	public void removeReadyPlayer(String sessionId) {
		readyPlayers.remove(sessionId);
	}

	public int getReadyPlayersSize() {
		return readyPlayers.size();
	}

	public Set<String> getReadyPlayers() {
		return readyPlayers;
	}

	public void setReadyPlayers(Set<String> readyPlayers) {
		this.readyPlayers = readyPlayers;
	}

	@Override
	public String toString() {
		return "AbstractGame [id=" + id + ", players=" + Arrays.toString(players) + ", readyPlayerSet=" + readyPlayers + ", joinCount=" + joinCount + "]";
	}

}
