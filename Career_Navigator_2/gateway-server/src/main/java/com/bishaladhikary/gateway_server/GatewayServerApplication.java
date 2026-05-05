package com.bishaladhikary.gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder)
	{
		return routeLocatorBuilder.routes()
				.route( p-> p
						.path("/cn/employee-service/**")
						.filters(f-> f.rewritePath("/cn/employees-service/(?<segment>).*","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://EMPLOYEE-SERVICE")


						)
				.build();


	}
}

