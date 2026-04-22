package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentRepository  extends JpaRepository<Department, Long> {

	boolean existsById(Long id);


	@Modifying
	@Query(value = "INSERT INTO department_employees (departmentId, employeeId) VALUES (:deptId, :empId)", nativeQuery = true)
	void addEmployeeToDepartment(@Param("deptId") Long deptId, @Param("empId") Long empId);
}
