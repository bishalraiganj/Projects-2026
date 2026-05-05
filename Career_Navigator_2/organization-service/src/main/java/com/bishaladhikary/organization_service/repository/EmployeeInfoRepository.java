package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
}
