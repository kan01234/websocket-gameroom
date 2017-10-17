package com.dotterbear.websocket.gameroom.model;
// package com.aeroplanechess.model;
//
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.concurrent.atomic.AtomicBoolean;
//
// public class TaskQueueRunner {
//
// Queue<Runnable> queue;
// AtomicBoolean running;
//
// public TaskQueueRunner() {
// running = new AtomicBoolean();
// running.set(false);
// queue = new LinkedList<Runnable>();
// }
//
// public void add(Runnable r) {
// queue.add(r);
// run();
// }
//
// void run() {
// if (running.compareAndSet(false, true)) {
// queue.poll().run();
// running.set(false);
// next();
// }
// }
//
// void next() {
// if (queue.isEmpty())
// return;
// run();
// }
//
// }
