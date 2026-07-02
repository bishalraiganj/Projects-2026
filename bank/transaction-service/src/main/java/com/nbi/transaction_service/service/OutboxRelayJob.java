package com.nbi.transaction_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.nbi.transaction_service.models.entities.OutboxEvent;
import com.nbi.transaction_service.models.events.TransferRequestedEvent;
import com.nbi.transaction_service.models.exception.KafkaOrOutboxEventSaveError;
import com.nbi.transaction_service.repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

@Service
public class OutboxRelayJob {


	private final Logger log = LoggerFactory.getLogger(OutboxRelayJob.class);

	private final OutboxEventRepository outboxEventRepository;
	private final KafkaTemplate<String, TransferRequestedEvent> kafkaTemplate;
	private final ObjectMapper mapper ;

	public OutboxRelayJob(KafkaTemplate<String, TransferRequestedEvent> kafkaTemplate,OutboxEventRepository outboxEventRepository, ObjectMapper mapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.outboxEventRepository = outboxEventRepository;
		this.mapper = mapper;
	}



	@Scheduled(fixedDelay = 2000)
	@Transactional
	public void publishPendingEvents()
	{

		log.info("OutboxRelayJob started... Publishing pending events");
		List<OutboxEvent> outboxEvents = outboxEventRepository.findByPublishedFalse();

		for(OutboxEvent outboxEvent : outboxEvents)
		{
			try{








            //Deserializing string jsonPayload back into actual type
			TransferRequestedEvent event  = mapper.readValue(outboxEvent.getPayload(),TransferRequestedEvent.class);






			//publishing then event to the corresponding kafka topic
			kafkaTemplate.send("transfer.requested",outboxEvent.getAggregateId(),event)
					.whenComplete((result,ex)->{
						if(ex==null)
						{
							//updating event publish status
							outboxEvent.setPublished(true);
							outboxEventRepository.save(outboxEvent);
							log.info("published transferRequested event for {}",outboxEvent);
						}else{
							log.error("Error while sending event or saving and changing outbox event status",ex);
							throw  new KafkaOrOutboxEventSaveError(ex.getMessage());
						}
					});


			}catch(Exception e)
			{
				log.error("Error while sending event or saving and changing outbox event status",e);
				throw  new KafkaOrOutboxEventSaveError(e.getMessage());
			}

			// if exception is thrown , outbox event publish stays false , for retries later



		}


	}
}

