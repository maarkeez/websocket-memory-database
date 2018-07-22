package com.memory.processer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
class ConcurrentProcesser<E> implements Processer<E> {

	final Queue<E> queue = new ConcurrentLinkedQueue<E>();
	private RunnableProcesser<E> processer;
	private Thread thread;

	ConcurrentProcesser() {
	}

	void init(@NotNull RunnableProcesser<E> processer) {
		this.processer = processer;
		thread = new Thread(processer);
		thread.start();
	}

	public void process(E element) {
		if (processer != null) {
			queue.add(element);
			processer.added();

		}
	}

	public void finish() {
		if (processer != null) {
			processer.finish();
		}
	}

}
