package com.bishaladhikary.employee.skill.dto;

public class AddEmployeeSkillRequest {

	private Long employeeId;
	private Long skillId;
	private int level;
	private String justification;

	public Long getEmployeeId() { return employeeId; }
	public Long getSkillId() { return skillId; }
	public int getLevel() { return level; }
	public String getJustification() { return justification; }
}