package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long> {

	boolean existsById(Long id);


	@Modifying
	@Query(value = "INSERT INTO department_employees (department_id, employees) VALUES (:deptId, :empId)", nativeQuery = true)
	void addEmployeeToDepartment(@Param("deptId") Long deptId, @Param("empId") Long empId);



	boolean existsByName(String name);



}
