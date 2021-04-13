package com.calculate.repo;

import org.springframework.data.repository.CrudRepository;

import com.calculate.model.OperationValueKey;
import com.calculate.model.Operations;

public interface OperationsRepository extends CrudRepository<Operations, OperationValueKey> {

}
