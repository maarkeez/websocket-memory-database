package com.memory.editor;

public interface Observer<E> {
	public void update(Aspect event, E element);
}
