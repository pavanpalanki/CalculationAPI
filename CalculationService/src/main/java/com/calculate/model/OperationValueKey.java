package com.calculate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OperationValueKey implements Serializable{
	
	private String operation;

	private Integer value1;

	private Integer value2;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getValue1() {
		return value1;
	}

	public void setValue1(Integer value1) {
		this.value1 = value1;
	}

	public Integer getValue2() {
		return value2;
	}

	public void setValue2(Integer value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return "OperationValueKey [operation=" + operation + ", value1=" + value1 + ", value2=" + value2 + "]";
	}
	
	

}
