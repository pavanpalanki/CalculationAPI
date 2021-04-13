package com.calculate;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;

import com.calculate.model.Message;
import com.calculate.model.OperationValueKey;
import com.calculate.model.Operations;
import com.calculate.model.Transactions;
import com.calculate.repo.OperationsRepository;
import com.calculate.repo.TransactionsRepository;

@SpringBootApplication
public class CalculationServiceApplication {

	private static final String QUEUE = "calculation-queue";

	@Autowired
	private OperationsRepository operationRepo;

	public static void main(String[] args) {
		SpringApplication.run(CalculationServiceApplication.class, args);
	}

	// @SqsListener listens to the message from the specified queue.
	@SqsListener(value = QUEUE, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void getMessageFromSqs(Message message, @Header("MessageId") String messageId) {
		System.out.println("Received message " + message.getOperation() + " with messageId " + messageId);
		System.out.println("Received message params" + message.getParam1() + " and " + message.getParam2());

		String operation = message.getOperation().toUpperCase();
		int param1 = message.getParam1();
		int param2 = message.getParam2();

		int result = 0;

		OperationValueKey value = new OperationValueKey();
		value.setOperation(operation);
		value.setValue1(param1);
		value.setValue2(param2);

		Operations op = new Operations();

		switch (operation) {
		case "ADD":
			result = param1 + param2;
			break;
		case "SUBSTRACT":
			result = param1 - param2;
			break;
		case "MULTIPLY":
			result = param1 * param2;
			break;
		case "DIVIDE":
			System.out.println("DIVIDING");
			result = param1 / param2;
			break;
		}

		Optional<Operations> findById = operationRepo.findById(value);
		if (findById.isEmpty()) {
			op.setOperationValueKey(value);
			op.setResult(result);
			Operations save = operationRepo.save(op);
			System.out.println(save.toString());

		} else {
			Operations operations = findById.get();
			System.out.println(operations.toString());
		}

	}
}
