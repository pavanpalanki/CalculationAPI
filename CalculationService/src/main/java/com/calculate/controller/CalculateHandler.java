package com.calculate.controller;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculate.model.Message;

@RestController
@RequestMapping(value = "/sqs")
public class CalculateHandler {

	private static final String QUEUE = "calculation-queue";

	// @SqsListener listens to the message from the specified queue.
//	@SqsListener(value = QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
//	public void getMessageFromSqs(String message, @Header("MessageId") String messageId) {
//		System.out.println("Received message " + message + " with messageId " + messageId);
//	}
}