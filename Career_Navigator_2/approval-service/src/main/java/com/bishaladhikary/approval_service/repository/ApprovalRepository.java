package com.bishaladhikary.approval_service.repository;

import com.bishaladhikary.approval_service.entity.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<ApprovalRequest, Long> {
}