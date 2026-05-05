package com.bishaladhikary.organization_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateRelationshipRequestDTO {



	@NotNull ( message = "Employee ID: cannot be null")
	@Positive
	private  Long employeeId;

	@NotNull( message = "Manager ID: cannot be null")
	@Positive
	private  Long managerId;

	@NotNull( message = "Department ID: cannot be null")
	@Positive
	private  Long departmentId;

	@NotNull( message = "Role ID: cannot be null")
	@Positive
	private  Long roleId;
}
