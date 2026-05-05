package com.bishaladhikary.approval_service.service;

import com.bishaladhikary.approval_service.entity.*;
import com.bishaladhikary.approval_service.event.ApprovalRejectedEvent;
import com.bishaladhikary.approval_service.exception.DBOperationFailed;
import com.bishaladhikary.approval_service.feign.OrganizationClient;
import com.bishaladhikary.approval_service.repository.ApprovalRepository;
import com.bishaladhikary.approval_service.producer.ApprovalEventProducer;

import com.bishaladhikary.approval_service.entity.enums.ApprovalStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApprovalService {

	private final ApprovalRepository repository;
	private final ApprovalEventProducer producer;
	private final OrganizationClient organizationclient;

	public ApprovalService(ApprovalRepository repository,
						   ApprovalEventProducer producer, OrganizationClient organizationclient) {
		this.repository = repository;
		this.producer = producer;
		this.organizationclient = organizationclient;
	}


	public List<ApprovalRequest> getAll(Long managerId)
	{
		return repository.findByApproverId(managerId);
	}

	public void approve(Long id, String comments) {

		ApprovalRequest request = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Approval not found"));

		request.setStatus(ApprovalStatus.APPROVED);
		request.setComments(comments);
		request.setDecidedAt(LocalDateTime.now());

		ApprovalRequest savedRequest = repository.save(request);

		if(savedRequest == null)
		{
			throw new DBOperationFailed("Database Operation Failed: XC001");
		}
		producer.publishApproved(
				new com.bishaladhikary.approval_service.event.ApprovalApprovedEvent(
						request.getId(),
						request.getSkillRequestId()
				)
		);
	}


	public void reject(Long id, String comments)
	{
		ApprovalRequest request = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("ApprovalRequest not found"));

		request.setStatus(ApprovalStatus.REJECTED);
		request.setComments(comments);
		request.setDecidedAt(LocalDateTime.now());

		ApprovalRequest savedRequest = repository.save(request);

		if(savedRequest == null)
		{
			throw new DBOperationFailed("Database Operation failed: XC001 ");
		}

		producer.publishRejected(new ApprovalRejectedEvent(savedRequest.getId(), savedRequest.getSkillRequestId()));
	}
}