package com.bishaladhikary.approval_service.event;

public class ApprovalRejectedEvent {

	private Long approvalRequestId;
	private Long skill_request_id;

	public ApprovalRejectedEvent(Long approvalRequestId, Long entityId) {
		this.approvalRequestId = approvalRequestId;
		this.skill_request_id = entityId;
	}

	public Long getApprovalRequestId() { return approvalRequestId; }
	public Long getEntityId() { return skill_request_id; }
}