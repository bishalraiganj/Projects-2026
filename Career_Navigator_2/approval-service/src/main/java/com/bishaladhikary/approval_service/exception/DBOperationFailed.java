package com.bishaladhikary.approval_service.exception;

public class DBOperationFailed extends RuntimeException {
	public DBOperationFailed(String message) {
		super(message);
	}
}
