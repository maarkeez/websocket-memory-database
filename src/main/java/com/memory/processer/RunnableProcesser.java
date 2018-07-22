package com.memory.processer;

import java.util.Queue;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;



@Validated
abstract class RunnableProcesser<E> implements Runnable {

	final Logger log = LogManager.getLogger(this.getClass());

	private boolean hasFinish = false;
	private boolean didNotified = false;
	private final Queue<E> queue;
	private final Object lock = new Object();

	public RunnableProcesser(@NotNull Queue<E> queue) {
		this.queue = queue;
	}

	public void run() {

		while (!hasFinish) {
			doWait();
			while (!hasFinish && !queue.isEmpty()) {
				process(queue.poll());
			}
		}
	}

	void added() {
		doNotify();
	}

	void finish() {
		hasFinish = true;
		doNotify();
	}

	private void doWait() {
		synchronized (lock) {
			while (!didNotified) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
				}
			}
			didNotified = false;
		}
	}

	private void doNotify() {
		synchronized (lock) {
			didNotified = true;
			lock.notify();
		}
	}

	abstract void process(E element);

}
