package com.dotterbear.websocket.gameroom.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGame {

	String id;
	Player[] players;
	Set<String> readyPlayerSet = new HashSet<String>();
	AtomicInteger joinCount = new AtomicInteger(0);
	// AtomicInteger turnCount = new AtomicInteger(-1);

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

	// public AtomicInteger getTurnCount() {
	// return turnCount;
	// }
	//
	// public void setTurnCount(AtomicInteger turnCount) {
	// this.turnCount = turnCount;
	// }


	public AtomicInteger getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(AtomicInteger joinCount) {
		this.joinCount = joinCount;
	}

	// public int getCurrentPlayerIndex() {
	// return turnCount.get() % players.length;
	// }

	public Set<String> getReadyPlayerSet() {
		return readyPlayerSet;
	}

	public void addReadyPlayer(String sessionId) {
		readyPlayerSet.add(sessionId);
	}

	public void removeReadyPlayer(String sessionId) {
		readyPlayerSet.remove(sessionId);
	}

	public int getReadyPlayerSize() {
		return readyPlayerSet.size();
	}

	public void setReadyPlayerSet(Set<String> readyPlayerSet) {
		this.readyPlayerSet = readyPlayerSet;
	}

	@Override
	public String toString() {
		return "AbstractGame [id=" + id + ", players=" + Arrays.toString(players) + ", readyPlayerSet=" + readyPlayerSet + ", joinCount=" + joinCount + "]";
	}

}
