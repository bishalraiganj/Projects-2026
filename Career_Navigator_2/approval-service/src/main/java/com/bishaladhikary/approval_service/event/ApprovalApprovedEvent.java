package com.bishaladhikary.approval_service.event;

public class ApprovalApprovedEvent {

	private Long approvalRequestId;
	private Long entityId;

	public ApprovalApprovedEvent(Long approvalRequestId, Long entityId) {
		this.approvalRequestId = approvalRequestId;
		this.entityId = entityId;
	}

	public Long getApprovalRequestId() { return approvalRequestId; }
	public Long getEntityId() { return entityId; }
}