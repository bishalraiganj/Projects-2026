package com.nbi.transaction_service.service;

import com.nbi.transaction_service.grpc_server.GrpcServer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class GrpcServerHandler {

	private final GrpcServer grpcServer;

	public GrpcServerHandler(GrpcServer grpcServer) {
		this.grpcServer = grpcServer;


	}

	@PostConstruct
	public void startServer()
	{
		grpcServer.start();
	}

	@PreDestroy
	public void stopServer()
	{
		grpcServer.shutdown();
	}


}
