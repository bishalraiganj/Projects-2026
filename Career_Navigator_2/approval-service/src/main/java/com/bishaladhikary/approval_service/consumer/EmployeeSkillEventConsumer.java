package com.bishaladhikary.approval_service.consumer;

import com.bishaladhikary.approval_service.entity.*;
import com.bishaladhikary.approval_service.event.EmployeeSkillSubmittedEvent;
import com.bishaladhikary.approval_service.exception.ManagerNotFoundException;
import com.bishaladhikary.approval_service.feign.OrganizationClient;
import com.bishaladhikary.approval_service.repository.ApprovalRepository;

import com.bishaladhikary.approval_service.entity.enums.ApprovalStatus;
import feign.FeignException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EmployeeSkillEventConsumer {

	private final ApprovalRepository repository;

	private final OrganizationClient organizationClient;


	public EmployeeSkillEventConsumer(ApprovalRepository repository, OrganizationClient organizationClient) {
		this.repository = repository;
		this.organizationClient = organizationClient;
	}

	@Bean
	public Consumer<EmployeeSkillSubmittedEvent> employeeSkillInput() {
		return event -> {

			try{
				EmployeeInfo em = organizationClient.findByEmployeeId(event.getEmployeeId());
				ApprovalRequest request = new ApprovalRequest();
				request.setSkillRequestId(event.getEmployeeSkillId());
				request.setRequesterId(event.getEmployeeId());
				request.setApproverId(em.getManagerId());
				request.setStatus(ApprovalStatus.PENDING);

				repository.save(request);
			}catch(FeignException.NotFound e)
			{
				throw new ManagerNotFoundException( "Manager not found or employee relationship not found in organization service !");
			}



		};
	}
}