package com.bishaladhikary.employee.skill.entity;

import com.bishaladhikary.employee.skill.entity.enums.SkillStatus;
import com.bishaladhikary.employee.skill.entity.AuditMetadata;
import jakarta.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_skills")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long employeeId;

	@Column(nullable = false)
	private Long skillId;

	@Column(nullable = false)
	private int level;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SkillStatus status;

	@Column(length = 500)
	private String justification;

	private Long approvalRequestId;

	@Column(nullable = false)
	private LocalDateTime lastUpdated;

	@Embedded
	private AuditMetadata audit;

	// 🔹 Default Constructor
	public EmployeeSkill() {}

	// 🔹 Getters and Setters

	public Long getId() {
		return id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SkillStatus getStatus() {
		return status;
	}

	public void setStatus(SkillStatus status) {
		this.status = status;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Long getApprovalRequestId() {
		return approvalRequestId;
	}

	public void setApprovalRequestId(Long approvalRequestId) {
		this.approvalRequestId = approvalRequestId;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public AuditMetadata getAudit() {
		return audit;
	}

	public void setAudit(AuditMetadata audit) {
		this.audit = audit;
	}
}