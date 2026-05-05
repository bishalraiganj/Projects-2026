package com.bishaladhikary.organization_service.dto;

import com.bishaladhikary.organization_service.entity.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class CreateRoleResponseDTO {

	private Long id;

	private String name;

	private List<Long> skills;


	private Department department;
}
