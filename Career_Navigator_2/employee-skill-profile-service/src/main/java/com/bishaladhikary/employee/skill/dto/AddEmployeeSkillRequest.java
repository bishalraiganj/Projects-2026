package com.bishaladhikary.employee.skill.dto;

public class AddEmployeeSkillRequest {

	private Long employeeId;
	private Long skillId;
	private int level;
	private String justification;

	public AddEmployeeSkillRequest(Long employeeId, Long skillId, int level, String justification) {
		this.employeeId = employeeId;
		this.skillId = skillId;
		this.level = level;
		this.justification = justification;
	}

	public Long getEmployeeId() { return employeeId; }
	public Long getSkillId() { return skillId; }
	public int getLevel() { return level; }
	public String getJustification() { return justification; }

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
}