package com.bishaladhikary.approval_service.event;

public class ApprovalApprovedEvent {

	private Long approvalRequestId;
	private Long skillRequestId;

	public ApprovalApprovedEvent(Long approvalRequestId, Long skillRequestId) {
		this.approvalRequestId = approvalRequestId;

		this.skillRequestId = skillRequestId;
	}

	public Long getApprovalRequestId() { return approvalRequestId; }
	public Long getSkillRequestId() { return skillRequestId; }
}