package com.memory.editor;

public interface Subject<E> {
	public void attach(Observer<E> observer);
	public void detach(Observer<E> observer);
}
