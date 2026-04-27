package com.bishaladhikary.organization_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateRelationshipRequestDTO {



	@NotNull ( message = "Employee ID: cannot be null")
	private  Long employeeId;

	@NotNull( message = "Manager ID: cannot be null")
	private  Long managerId;

	@NotNull( message = "Department ID: cannot be null")
	private  Long departmentId;

	@NotNull( message = "Role ID: cannot be null")
	private  Long roleId;
}
