package com.bishaladhikary.employee.skill.entity;

import jakarta.persistence.Embeddable;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
public class AuditMetadata {

	@CreatedBy
	private String createdBy;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedBy
	private String updatedBy;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public String getCreatedBy() { return createdBy; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public String getUpdatedBy() { return updatedBy; }
	public LocalDateTime getUpdatedAt() { return updatedAt; }
}