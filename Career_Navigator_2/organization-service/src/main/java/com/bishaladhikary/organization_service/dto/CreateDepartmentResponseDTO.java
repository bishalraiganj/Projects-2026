package com.bishaladhikary.organization_service.dto;

import com.bishaladhikary.organization_service.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;


@Getter
@Setter
@RequiredArgsConstructor
public class CreateDepartmentResponseDTO {


	private Long id;

	private String name;


	private HashSet<Long> employees = new LinkedHashSet<>();


	private HashSet<Role> roles;
}
