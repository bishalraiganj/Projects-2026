package com.bishaladhikary.approval_service.entity;

import com.bishaladhikary.approval_service.entity.enums.ApprovalStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "approval_requests")
public class ApprovalRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long skillRequestId;
	private Long requesterId;
	private Long approverId;

	@Enumerated(EnumType.STRING)
	private ApprovalStatus status;

	private String comments;
	private LocalDateTime decidedAt;

	public Long getId() { return id; }

	public Long getSkillRequestId() { return skillRequestId; }
	public void setSkillRequestId(Long entityId) { this.skillRequestId = entityId; }

	public Long getRequesterId() { return requesterId; }
	public void setRequesterId(Long requesterId) { this.requesterId = requesterId; }

	public Long getApproverId() { return approverId; }
	public void setApproverId(Long approverId) { this.approverId = approverId; }

	public ApprovalStatus getStatus() { return status; }
	public void setStatus(ApprovalStatus status) { this.status = status; }

	public String getComments() { return comments; }
	public void setComments(String comments) { this.comments = comments; }

	public LocalDateTime getDecidedAt() { return decidedAt; }
	public void setDecidedAt(LocalDateTime decidedAt) { this.decidedAt = decidedAt; }
}