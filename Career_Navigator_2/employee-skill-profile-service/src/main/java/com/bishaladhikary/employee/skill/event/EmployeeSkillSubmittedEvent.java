package com.bishaladhikary.employee.skill.event;

import java.time.LocalDateTime;

public class EmployeeSkillSubmittedEvent {

	private Long employeeSkillId;
	private Long employeeId;
	private Long skillId;
	private int level;
	private LocalDateTime timestamp;

	public EmployeeSkillSubmittedEvent(Long employeeSkillId, Long employeeId,
									   Long skillId, int level, LocalDateTime timestamp) {
		this.employeeSkillId = employeeSkillId;
		this.employeeId = employeeId;
		this.skillId = skillId;
		this.level = level;
		this.timestamp = timestamp;
	}

	public Long getEmployeeSkillId() { return employeeSkillId; }
	public Long getEmployeeId() { return employeeId; }
	public Long getSkillId() { return skillId; }
	public int getLevel() { return level; }
	public LocalDateTime getTimestamp() { return timestamp; }
}