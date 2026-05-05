package com.bishaladhikary.employee.skill.event;

public class ApprovalRejectedEvent {

	private Long approvalRequestId;
	private Long skillRequestId;

	public ApprovalRejectedEvent(Long approvalRequestId, Long skillRequestId) {
		this.approvalRequestId = approvalRequestId;

		this.skillRequestId = skillRequestId;
	}

	public Long getApprovalRequestId() { return approvalRequestId; }
	public Long getSkillRequestId() { return skillRequestId; }

	public void setApprovalRequestId(Long approvalRequestId) {
		this.approvalRequestId = approvalRequestId;
	}

	public void setSkillRequestId(Long skillRequestId) {
		this.skillRequestId = skillRequestId;
	}
}