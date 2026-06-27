package com.nbi.transaction_service.models.exception;

public class TransferRequestedEventPayloadCreationFailed  extends RuntimeException{

	 public TransferRequestedEventPayloadCreationFailed(String message){
		super(message);
	}
}
