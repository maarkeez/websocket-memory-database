package com.memory.web.websocket.controller;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.memory.editor.Aspect;
import com.memory.editor.Observer;
import com.memory.editor.custom.CustomDataSubject;
import com.memory.persistence.entity.CustomData;
import com.memory.web.websocket.message.InputWebSocket;
import com.memory.web.websocket.message.OutputWebSocket;
@Controller
public class WebSocketController implements Observer<CustomData> {

	private final Logger log = LogManager.getLogger(this.getClass());
	private SimpMessagingTemplate template;

	@Autowired
	private CustomDataSubject customDataSubject;

	@Autowired
	public WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@PostConstruct
	public void init() {
		customDataSubject.attach(this);
	}

	@MessageMapping("/customdata")
	@SendTo("/topic/customdata")
	public OutputWebSocket<CustomData> greeting(InputWebSocket message) throws Exception {
		log.info("======> INPUT websocket-customdata: " + message);
		Thread.sleep(1000); // simulated delay
		CustomData data = new CustomData();
		data.setData1(new Random().nextLong());
		data.setData2(new Random().nextLong());

		OutputWebSocket<CustomData> output = new OutputWebSocket<CustomData>();
		output.setData(data);

		log.info("<====== OUTPUT websocket-customdata: " + output + ", for: " + message);

		return output;
	}

	@Override
	public void update(Aspect event, CustomData element) {
		OutputWebSocket<CustomData> output = new OutputWebSocket<CustomData>();
		output.setData(element);
		output.setAspect(event);
		log.info("<====== OUTPUT websocket-customdata: " + output + ", for: " + event);
		this.template.convertAndSend("/topic/customdata", output);
	}

}
