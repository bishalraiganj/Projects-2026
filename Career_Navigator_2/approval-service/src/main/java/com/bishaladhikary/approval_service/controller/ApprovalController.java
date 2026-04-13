package com.bishaladhikary.approval_service.controller;

import com.bishaladhikary.approval_service.dto.ApprovalDecisionRequest;
import com.bishaladhikary.approval_service.service.ApprovalService;

import com.bishaladhikary.approval_service.dto.ApprovalDecisionRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

	private final ApprovalService service;

	public ApprovalController(ApprovalService service) {
		this.service = service;
	}

	@PostMapping("/{id}/approve")
	public void approve(@PathVariable Long id,
						@RequestBody ApprovalDecisionRequest request) {

		service.approve(id, request.getComments());
	}
}