package com.bishaladhikary.approval_service.repository;

import com.bishaladhikary.approval_service.entity.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<ApprovalRequest, Long> {


	List<ApprovalRequest> findByApproverId(Long id);

}