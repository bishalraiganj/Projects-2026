package com.bishaladhikary.employee.skill.consumer;

import com.bishaladhikary.employee.skill.entity.EmployeeSkill;
import com.bishaladhikary.employee.skill.entity.enums.SkillStatus;
import com.bishaladhikary.employee.skill.event.ApprovalApprovedEvent;
import com.bishaladhikary.employee.skill.repository.EmployeeSkillRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ApprovalEventConsumer {

	private final EmployeeSkillRepository repository;

	public ApprovalEventConsumer(EmployeeSkillRepository repository) {
		this.repository = repository;
	}

	@Bean
	public Consumer<ApprovalApprovedEvent> approvalInput() {
		return event -> {

			EmployeeSkill skill = repository.findById(event.getEntityId())
					.orElseThrow();

			// idempotency check
			if (skill.getStatus() == SkillStatus.APPROVED) {
				return;
			}

			skill.setStatus(SkillStatus.APPROVED);
			skill.setApprovalRequestId(event.getApprovalRequestId());

			repository.save(skill);
		};
	}
}