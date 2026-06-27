package com.nbi.transaction_service.config;

import com.nbi.transaction_service.grpc_server.GrpcServer;
import com.nbi.transaction_service.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerBeanConfig {

	private final TransactionService transactionService;


	public GrpcServerBeanConfig(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@Bean
	public GrpcServer grpcServer()
	{
		return GrpcServer.create(transactionService);
	}

}
