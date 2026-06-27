package com.nbi.transaction_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbi.transaction_service.grpc_server.GrpcServer;
import com.nbi.transaction_service.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperBeanConfig {


//	private TransferRepository transferRepository;
//	private LedgerRepository ledgerRepository;
//	private OutboxEventRepository outboxEventRepository;
//
//
//	private OutboxRowPersistService outboxRowPersistService;
//
//
//	public ObjectMapperBeanConfig(TransferRepository transferRepository, LedgerRepository ledgerRepository, OutboxEventRepository outboxEventRepository, OutboxRowPersistService outboxRowPersistService) {
//		this.transferRepository = transferRepository;
//		this.ledgerRepository = ledgerRepository;
//		this.outboxEventRepository = outboxEventRepository;
//		this.outboxRowPersistService = outboxRowPersistService;
//	}


	@Bean
	public ObjectMapper objectMapper() {

		return new ObjectMapper();
	}


}
