package com.bishaladhikary.organization_service.dto;

import com.bishaladhikary.organization_service.entity.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateRoleRequestDTO {
	@NotBlank(message = "Role name cannot be null")
	private String name;

	private List<Long> skills;


	@NotNull(message = "Department object data cannot be blank")
	private Long departmentId;
}
