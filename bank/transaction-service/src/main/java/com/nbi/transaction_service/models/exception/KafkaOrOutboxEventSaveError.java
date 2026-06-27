package com.nbi.transaction_service.models.exception;

public class KafkaOrOutboxEventSaveError extends RuntimeException{

	public KafkaOrOutboxEventSaveError(String message){
		super(message);
	}
}
