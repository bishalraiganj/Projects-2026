package com.bishaladhikary.organization_service.dto;

import com.bishaladhikary.organization_service.entity.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
public class CreateDepartmentRequestDTO {


	@NotBlank( message = " Department Name cannot be null")
	private String name;


	private Set<Long> employees = new HashSet<>() ;



}
