package com.nbi.transaction_service.grpc_server;


import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class GrpcServer {


	private final Logger logger = LoggerFactory.getLogger(GrpcServer.class);

	private final Server server ;

	private GrpcServer(Server server)
	{
		this.server = server;
	}

	private static  GrpcServer create(int port, BindableService... bindableServices)
	{
		var builder = ServerBuilder.forPort(port);
		Arrays.asList(bindableServices).forEach((s)->builder.addService(s));
		return new GrpcServer(builder.build());
	}

	public static GrpcServer create(BindableService... bindableServices)
	{
		return create(6565,bindableServices);
	}


	public GrpcServer start()
	{
		try{
			server.start();
			ArrayList<String> services = server.getServices()
					.stream()
					.map((s)->s.getServiceDescriptor().getName())
					.collect(()->new ArrayList<>(),
							(ArrayList<String> l1, String s1)-> l1.add(s1),
							(l3,l4)->l3.addAll(l4));
			logger.info("Server started, on port: {} , services: {}",server.getPort(),services);

		}catch(Exception e){

			logger.error(e.getMessage(),e,e.getCause());
		}
		return this;

	}

	public GrpcServer awaitTermination()
	{
		try{
			server.awaitTermination();
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
			logger.error(e.getMessage(),e);
		}
		return this;
	}

	public GrpcServer shutdown()
	{
		try{
			server.shutdown();
			logger.info("Server  on port {} shutting down: ",server.getPort());
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		return this;
	}


}
