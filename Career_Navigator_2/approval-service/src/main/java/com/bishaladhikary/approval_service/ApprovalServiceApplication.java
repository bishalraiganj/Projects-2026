package com.bishaladhikary.approval_service;

import com.bishaladhikary.approval_service.feign.OrganizationClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.bishaladhikary.approval_service.feign")
public class ApprovalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApprovalServiceApplication.class, args);
	}

}
