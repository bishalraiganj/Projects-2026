package com.bishaladhikary.employee_service.exception;

public class DBOperationFailedException extends RuntimeException {
	public DBOperationFailedException(String message) {
		super(message);
	}
}
