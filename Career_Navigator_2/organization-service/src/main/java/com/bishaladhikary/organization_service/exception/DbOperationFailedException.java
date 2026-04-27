package com.bishaladhikary.organization_service.exception;

public class DbOperationFailedException extends RuntimeException {
	public DbOperationFailedException(String message) {
		super(message);
	}
}
