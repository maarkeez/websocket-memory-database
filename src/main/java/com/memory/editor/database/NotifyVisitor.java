package com.memory.editor.database;

import org.springframework.data.repository.CrudRepository;

import com.memory.editor.Aspect;
import com.memory.editor.INotifyVisitor;
import com.memory.editor.Observer;

import lombok.Data;

@Data
public class NotifyVisitor<E, ID> implements INotifyVisitor{

	private final CrudRepository<E, ID> repository;
	private final Aspect aspect;
	private final E element;
	private final Iterable<Observer<E>> observers;

	public void notifyObservers() {
		switch (aspect) {
		case CREATE:
		case UPDATE:
			getRepository().save(getElement());
			break;
		case DELETE:
			getRepository().delete(getElement());
			break;
		default:
			break;
		}
		observers.forEach(observer -> observer.update(aspect,element));
	}

}
