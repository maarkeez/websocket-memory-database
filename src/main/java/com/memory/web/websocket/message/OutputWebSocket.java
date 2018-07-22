package com.memory.web.websocket.message;

import com.memory.editor.Aspect;

import lombok.Data;

@Data
public class OutputWebSocket<E> {
	private E data;
	private Aspect aspect;
}
