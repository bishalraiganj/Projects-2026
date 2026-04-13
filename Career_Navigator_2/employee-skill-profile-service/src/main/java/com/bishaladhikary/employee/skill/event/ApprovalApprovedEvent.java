package com.bishaladhikary.employee.skill.event;

public class ApprovalApprovedEvent {

	private Long approvalRequestId;
	private Long entityId;

	public Long getApprovalRequestId() { return approvalRequestId; }
	public Long getEntityId() { return entityId; }

	public void setApprovalRequestId(Long approvalRequestId) {
		this.approvalRequestId = approvalRequestId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
}