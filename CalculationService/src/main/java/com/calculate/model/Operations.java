package com.calculate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Operations {
	
	@Id
	 private OperationValueKey operationValueKey;
	
	 private int result;

	public OperationValueKey getOperationValueKey() {
		return operationValueKey;
	}

	public void setOperationValueKey(OperationValueKey operationValueKey) {
		this.operationValueKey = operationValueKey;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Operations [operationValueKey=" + operationValueKey + ", result=" + result + "]";
	}
	 
	 

}
