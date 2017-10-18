package com.dotterbear.websocket.gameroom.model;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGame {

	String id;
	Player[] players;
	AtomicInteger readyCount = new AtomicInteger(0);
	AtomicInteger joinCount = new AtomicInteger(0);
	AtomicInteger turnCount = new AtomicInteger(-1);
	// TaskQueueRunner taskQueueRunner = new TaskQueueRunner();

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

	public AtomicInteger getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(AtomicInteger turnCount) {
		this.turnCount = turnCount;
	}

	public AtomicInteger getReadyCount() {
		return readyCount;
	}

	public void setReadyCount(AtomicInteger readyCount) {
		this.readyCount = readyCount;
	}

	public AtomicInteger getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(AtomicInteger joinCount) {
		this.joinCount = joinCount;
	}

	// public TaskQueueRunner getTaskQueueRunner() {
	// return taskQueueRunner;
	// }
	//
	// public void setTaskQueueRunner(TaskQueueRunner taskQueueRunner) {
	// this.taskQueueRunner = taskQueueRunner;
	// }
	//
	// public void addTask(Runnable runnable) {
	// taskQueueRunner.add(runnable);
	// }

	public int getCurrentPlayerIndex() {
		return turnCount.get() % players.length;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", players=" + Arrays.toString(players) + ", readyCount=" + readyCount + ", joinCount=" + joinCount + ", turnCount=" + turnCount + "]";
	}

}
