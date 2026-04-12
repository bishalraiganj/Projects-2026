package com.bishaladhikary.employee.skill.service;

import com.bishaladhikary.employee.skill.dto.*;
import com.bishaladhikary.employee.skill.entity.EmployeeSkill;
import com.bishaladhikary.employee.skill.entity.enums.SkillStatus;
import com.bishaladhikary.employee.skill.event.EmployeeSkillSubmittedEvent;
import com.bishaladhikary.employee.skill.feign.SkillClient;
import com.bishaladhikary.employee.skill.producer.EmployeeSkillEventProducer;
import com.bishaladhikary.employee.skill.repository.EmployeeSkillRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeSkillService {

	private final EmployeeSkillRepository repository;
	private final SkillClient skillClient;
	private final EmployeeSkillEventProducer producer;

	public EmployeeSkillService(EmployeeSkillRepository repository,
								SkillClient skillClient,
								EmployeeSkillEventProducer producer) {
		this.repository = repository;
		this.skillClient = skillClient;
		this.producer = producer;
	}

	public EmployeeSkillResponse addSkill(AddEmployeeSkillRequest request) {

		// 1. Validate skill via Feign
		SkillResponse skill = skillClient.getSkill(request.getSkillId());

		if (request.getLevel() < skill.getMinLevel() ||
				request.getLevel() > skill.getMaxLevel()) {
			throw new RuntimeException("Invalid skill level");
		}

		// 2. Create entity
		EmployeeSkill entity = new EmployeeSkill();
		entity.setEmployeeId(request.getEmployeeId());
		entity.setSkillId(request.getSkillId());
		entity.setLevel(request.getLevel());
		entity.setStatus(SkillStatus.PENDING);
		entity.setJustification(request.getJustification());
		entity.setLastUpdated(LocalDateTime.now());

		// 3. Save to DB
		EmployeeSkill saved = repository.save(entity);

		// 4. Create event
		EmployeeSkillSubmittedEvent event =
				new EmployeeSkillSubmittedEvent(
						saved.getId(),
						saved.getEmployeeId(),
						saved.getSkillId(),
						saved.getLevel(),
						LocalDateTime.now()
				);

		// 5. Publish event
		producer.publishSkillSubmitted(event);

		// 6. Return response
		return map(saved);
	}

	private EmployeeSkillResponse map(EmployeeSkill e) {
		return new EmployeeSkillResponse(
				e.getId(),
				e.getEmployeeId(),
				e.getSkillId(),
				e.getLevel(),
				e.getStatus().name(),
				e.getLastUpdated()
		);
	}
}