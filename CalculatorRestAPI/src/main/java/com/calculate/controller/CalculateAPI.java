package com.calculate.controller;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.calculate.controller.model.Message;

@RestController
@RequestMapping(value = "/altran")
public class CalculateAPI {

	private static final String QUEUE = "calculation-queue";

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@PostMapping(value = "/send")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String sendMessageToSqs(@RequestBody final Message message) {
		ArrayList<String> validOperations = new ArrayList<String>();

		validOperations.add("ADD");
		validOperations.add("SUBSTRACT");
		validOperations.add("MULTIPLY");
		validOperations.add("DIVIDE");

		if (message.getOperation() != null && !"".equals(message.getOperation())
				&& validOperations.contains(message.getOperation().toUpperCase())
				&& (message.getParam1() instanceof Integer) && (message.getParam2() instanceof Integer)) {
			queueMessagingTemplate.convertAndSend(QUEUE, message);
			return "Successfully sent to calculation-queue";
		} else {
			return "Validation failed";
		}

	}

}
