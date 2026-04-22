package com.bishaladhikary.employee.skill.exception;

public class DBOperationFailedException extends RuntimeException {
	public DBOperationFailedException(String message) {
		super(message);
	}
}
