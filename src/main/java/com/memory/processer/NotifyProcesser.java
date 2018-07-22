package com.memory.processer;

import java.util.Queue;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.memory.editor.INotifyVisitor;

@Validated
class NotifyProcesser extends RunnableProcesser<INotifyVisitor> {

	public NotifyProcesser(@NotNull Queue<INotifyVisitor> queue) {
		super(queue);
	}

	@Override
	void process(INotifyVisitor visitor) {
		log.info("Process: " + visitor);
		visitor.notifyObservers();
	}
}
