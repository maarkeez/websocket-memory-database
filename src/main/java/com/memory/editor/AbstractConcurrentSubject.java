package com.memory.editor;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.data.repository.CrudRepository;

import com.memory.processer.NotifyProcesserSingleThread;
import com.memory.processer.Processer;

public abstract class AbstractConcurrentSubject<E, ID> implements Subject<E> {

	private final CrudRepository<E, ID> repository;

	private Set<Observer<E>> observers = new CopyOnWriteArraySet<Observer<E>>();

	public AbstractConcurrentSubject(CrudRepository<E, ID> repository) {
		this.repository = repository;
	}

	public abstract void init();

	public void attach(Observer<E> observer) throws NullPointerException {
		getObservers().add(observer);
	}

	public void detach(Observer<E> observer) {
		getObservers().remove(observer);
	}

	protected Set<Observer<E>> getObservers() {
		return observers;
	}

	protected Processer<INotifyVisitor> getProcesser() {
		return NotifyProcesserSingleThread.instance();
	}

	protected CrudRepository<E, ID> getRepository() {
		return repository;
	}

	protected void setObservers(Set<Observer<E>> observers) {
		this.observers = observers;
	}
}
