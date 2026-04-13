package com.bishaladhikary.approval_service.consumer;

import com.bishaladhikary.approval_service.entity.*;
import com.bishaladhikary.approval_service.event.EmployeeSkillSubmittedEvent;
import com.bishaladhikary.approval_service.repository.ApprovalRepository;

import com.bishaladhikary.approval_service.entity.enums.ApprovalStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EmployeeSkillEventConsumer {

	private final ApprovalRepository repository;

	public EmployeeSkillEventConsumer(ApprovalRepository repository) {
		this.repository = repository;
	}

	@Bean
	public Consumer<EmployeeSkillSubmittedEvent> employeeSkillInput() {
		return event -> {
			ApprovalRequest request = new ApprovalRequest();
			request.setEntityId(event.getEmployeeSkillId());
			request.setRequesterId(event.getEmployeeId());
			request.setApproverId(1001L);
			request.setStatus(ApprovalStatus.PENDING);

			repository.save(request);
		};
	}
}