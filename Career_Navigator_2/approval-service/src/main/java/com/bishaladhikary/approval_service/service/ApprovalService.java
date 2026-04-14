package com.bishaladhikary.approval_service.service;

import com.bishaladhikary.approval_service.entity.*;
import com.bishaladhikary.approval_service.repository.ApprovalRepository;
import com.bishaladhikary.approval_service.producer.ApprovalEventProducer;

import com.bishaladhikary.approval_service.entity.enums.ApprovalStatus;
import com.bishaladhikary.approval_service.repository.ApprovalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApprovalService {

	private final ApprovalRepository repository;
	private final ApprovalEventProducer producer;

	public ApprovalService(ApprovalRepository repository,
						   ApprovalEventProducer producer) {
		this.repository = repository;
		this.producer = producer;
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

		repository.save(request);

		producer.publishApproved(
				new com.bishaladhikary.approval_service.event.ApprovalApprovedEvent(
						request.getId(),
						request.getEntityId()
				)
		);
	}
}