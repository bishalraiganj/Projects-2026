package com.bishaladhikary.employee.skill.event;

public class ApprovalApprovedEvent {

	private Long approvalRequestId;
	private Long skillRequestId;

	public ApprovalApprovedEvent(Long approvalRequestId, Long skillRequestId) {
		System.out.println("ApprovalApprovedEvent printed");
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