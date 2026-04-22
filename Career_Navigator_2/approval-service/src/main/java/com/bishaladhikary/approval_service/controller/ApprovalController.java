package com.bishaladhikary.approval_service.controller;

import com.bishaladhikary.approval_service.dto.ApprovalDecisionRequest;
import com.bishaladhikary.approval_service.entity.ApprovalRequest;
import com.bishaladhikary.approval_service.service.ApprovalService;

import com.bishaladhikary.approval_service.dto.ApprovalDecisionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


	@PostMapping("/{id}/reject")
	public void reject(@PathVariable Long id, @RequestBody ApprovalDecisionRequest request)
	{
		service.reject(id, request.getComments());
	}

	@GetMapping("/getAll/{id}")
	public ResponseEntity<List<ApprovalRequest>> getAllApprovalRequests(@PathVariable("id") Long managerId)
	{
		return ResponseEntity.ok(service.getAll(managerId));

	}
}