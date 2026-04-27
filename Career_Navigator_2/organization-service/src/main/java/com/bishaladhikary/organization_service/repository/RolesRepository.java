package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.Role;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface RolesRepository  extends JpaRepository<Role, Long> {

	boolean existsById(Long id);


//	@Modifying
//	@Query(value = "INSERT INTO roles (role_id,department_id) VALUES (:roleId, :departmentId) ", nativeQuery = true)
//	Role addDepartmentToRole(@Param("roleId") Long roleId, @Param("departmentId") Long departmentId);
}
