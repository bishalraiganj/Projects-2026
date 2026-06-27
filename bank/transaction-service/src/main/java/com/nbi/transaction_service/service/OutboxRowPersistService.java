package com.nbi.transaction_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbi.transaction_service.models.exception.TransferInitiationFailed;
import com.nbi.transaction_service.models.entities.OutboxEvent;
import com.nbi.transaction_service.models.entities.Transfer;
import com.nbi.transaction_service.models.events.TransferRequestedEvent;
import com.nbi.transaction_service.models.exception.TransferRequestedEventPayloadCreationFailed;
import com.nbi.transaction_service.repository.OutboxEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OutboxRowPersistService {

	private static final Logger log = LoggerFactory.getLogger(OutboxRowPersistService.class);
	private OutboxEventRepository outboxEventRepository;

	public  OutboxRowPersistService(OutboxEventRepository outboxEventRepository) {
		this.outboxEventRepository = outboxEventRepository;
	}


	public String publishTransferInitiated(OutboxEvent outboxEvent, Transfer savedTransfer) {


		//payload creation and setter logic :-)

		ObjectMapper mapper = new ObjectMapper();

		TransferRequestedEvent event = TransferRequestedEvent.builder()
				.transferId(savedTransfer.getId())
				.fromAccountId(savedTransfer.getFromAccountId())
				.toAccountId(savedTransfer.getToAccountId())
				.amount(savedTransfer.getAmount().toString())
				.status("TRANSFER_REQUESTED")
				.build();

		//creating json and then to string
		String payloadJson;
		try{
			payloadJson = mapper.writeValueAsString(event);
	}catch(JsonProcessingException e)
		{
			log.error("JsonProcessingException in publishTransferInitiated method  ",e.getMessage());
			throw new TransferRequestedEventPayloadCreationFailed(e.getMessage());
		}


		outboxEvent.setPayload(payloadJson);


		try{
			outboxEventRepository.save(outboxEvent);
		}catch(Exception e){
			log.error("Error while saving transfer",e);
			throw new TransferInitiationFailed("Error while saving transfer's outbox event");
		}
		return outboxEvent.getAggregateId();
	}

}
