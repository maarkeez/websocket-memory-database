package com.memory.processer;

import com.memory.editor.INotifyVisitor;

public class NotifyProcesserSingleThread extends ConcurrentProcesser<INotifyVisitor> {

	private final NotifyProcesser drProc = new NotifyProcesser(this.queue);
	private static final NotifyProcesserSingleThread instance = new NotifyProcesserSingleThread();

	private NotifyProcesserSingleThread() {
		super();
		init(drProc);
	}

	public static ConcurrentProcesser<INotifyVisitor> instance() {
		return instance;
	}

}
