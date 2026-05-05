package com.bishaladhikary.employee.skill.dto;

import java.time.LocalDateTime;

public class EmployeeSkillResponse {

	private Long id;
	private Long employeeId;
	private Long skillId;
	private int level;
	private String status;
	private LocalDateTime lastUpdated;

	public EmployeeSkillResponse(Long id, Long employeeId, Long skillId,
								 int level, String status, LocalDateTime lastUpdated) {
		this.id = id;
		this.employeeId = employeeId;
		this.skillId = skillId;
		this.level = level;
		this.status = status;
		this.lastUpdated = lastUpdated;
	}
}