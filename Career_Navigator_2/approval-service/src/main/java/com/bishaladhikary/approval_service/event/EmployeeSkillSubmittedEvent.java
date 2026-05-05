package com.bishaladhikary.approval_service.event;

import java.time.LocalDateTime;

public class EmployeeSkillSubmittedEvent {

	private Long employeeSkillId;
	private Long employeeId;
	private Long skillId;
	private int level;
	private LocalDateTime timestamp;

	public Long getEmployeeSkillId() { return employeeSkillId; }
	public Long getEmployeeId() { return employeeId; }
	public Long getSkillId() { return skillId; }
	public int getLevel() { return level; }
	public LocalDateTime getTimestamp() { return timestamp; }

	public void setEmployeeSkillId(Long employeeSkillId) { this.employeeSkillId = employeeSkillId; }
	public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
	public void setSkillId(Long skillId) { this.skillId = skillId; }
	public void setLevel(int level) { this.level = level; }
	public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}