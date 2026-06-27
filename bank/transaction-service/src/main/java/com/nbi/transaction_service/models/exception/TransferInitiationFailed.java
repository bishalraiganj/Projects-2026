package com.nbi.transaction_service.models.exception;

public class TransferInitiationFailed extends RuntimeException {
	public TransferInitiationFailed(String message) {
		super(message);
	}
}
