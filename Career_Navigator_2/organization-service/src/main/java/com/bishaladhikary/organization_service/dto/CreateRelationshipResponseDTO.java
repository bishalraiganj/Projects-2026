package com.bishaladhikary.organization_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateRelationshipResponseDTO {




	private  Long employeeId;

	private  Long managerId;

	private  Long departmentId;

	private  Long roleId;
}
