package com.bishaladhikary.employee.skill.consumer;

import com.bishaladhikary.employee.skill.entity.EmployeeSkill;
import com.bishaladhikary.employee.skill.entity.enums.SkillStatus;
import com.bishaladhikary.employee.skill.event.ApprovalApprovedEvent;
import com.bishaladhikary.employee.skill.event.ApprovalRejectedEvent;
import com.bishaladhikary.employee.skill.exception.DBOperationFailedException;
import com.bishaladhikary.employee.skill.repository.EmployeeSkillRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
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

			EmployeeSkill skill = repository.findById(event.getSkillRequestId())
					.orElseThrow(()->new RuntimeException("Employee skill not found"));

			// idempotency check
			if (skill.getStatus() == SkillStatus.APPROVED) {
				return;
			}

			skill.setStatus(SkillStatus.APPROVED);
			skill.setApprovalRequestId(event.getApprovalRequestId());

			EmployeeSkill savedSkill = repository.save(skill);
			if(savedSkill == null)
			{
				throw new DBOperationFailedException(" Database Operation Failed : XC001 , Failed to save updates : " + skill.getStatus());
			}

			System.out.println("Approval consumer function ran:" + LocalDateTime.now());
		};
	}



	@Bean
	public Consumer<ApprovalRejectedEvent> rejectedInput() {

		return event -> {


			EmployeeSkill skill = repository.findById(event.getSkillRequestId())
					.orElseThrow(()-> new DBOperationFailedException("Database Operation Failed: XC001"));

			//Idempotency Check
			if(skill.getStatus() == SkillStatus.REJECTED) return;

			skill.setStatus(SkillStatus.REJECTED);
			skill.setApprovalRequestId(event.getApprovalRequestId());
			EmployeeSkill savedSkill = repository.save(skill);

			if(savedSkill == null)
			{
				throw new DBOperationFailedException("Database Operation Failed : XC0001, Failed to save updates : " + skill.getStatus());
			}

			System.out.println("Rejected consumer function ran:" + LocalDateTime.now());


		};

	}
}