package com.bishaladhikary.employee.skill.service;

import com.bishaladhikary.employee.skill.dto.*;
import com.bishaladhikary.employee.skill.entity.Employee;
import com.bishaladhikary.employee.skill.entity.EmployeeSkill;
import com.bishaladhikary.employee.skill.entity.enums.SkillStatus;
import com.bishaladhikary.employee.skill.event.EmployeeSkillSubmittedEvent;
import com.bishaladhikary.employee.skill.exception.EmployeeNotFoundException;
import com.bishaladhikary.employee.skill.feign.EmployeeClient;
import com.bishaladhikary.employee.skill.feign.SkillClient;
import com.bishaladhikary.employee.skill.producer.EmployeeSkillEventProducer;
import com.bishaladhikary.employee.skill.repository.EmployeeSkillRepository;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeSkillService {

	private final EmployeeSkillRepository repository;
	private final SkillClient skillClient;
	private final EmployeeClient employeeClient;
	private final EmployeeSkillEventProducer producer;

	public EmployeeSkillService(EmployeeSkillRepository repository,
								SkillClient skillClient, EmployeeClient employeeClient,
								EmployeeSkillEventProducer producer) {
		this.repository = repository;
		this.skillClient = skillClient;
		this.employeeClient = employeeClient;
		this.producer = producer;
	}

	public EmployeeSkillResponse addSkill(AddEmployeeSkillRequest request) {


		//Validating if employee by that id exists
		try {
			Employee employee = employeeClient.getEmployee(request.getEmployeeId());

		}catch(FeignException.NotFound e)
		{
			throw new EmployeeNotFoundException("Employee by id: " + request.getEmployeeId() + "not found");
		}




		//  Validate skill via Feign
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
		producer.publish(event);

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