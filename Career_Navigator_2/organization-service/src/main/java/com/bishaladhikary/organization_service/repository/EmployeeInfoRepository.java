package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
}
