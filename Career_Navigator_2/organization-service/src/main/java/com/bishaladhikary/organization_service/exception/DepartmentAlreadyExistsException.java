package com.bishaladhikary.organization_service.exception;

public class DepartmentAlreadyExistsException extends RuntimeException {
	public DepartmentAlreadyExistsException(String message) {
		super(message);
	}
}
