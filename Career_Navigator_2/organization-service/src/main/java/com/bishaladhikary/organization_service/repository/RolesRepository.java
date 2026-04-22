package com.bishaladhikary.organization_service.repository;

import com.bishaladhikary.organization_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository  extends JpaRepository<Role, Long> {

	boolean existsById(Long id);
}
