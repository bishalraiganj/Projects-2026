package com.bishaladhikary.employee_service.entity.dto;

import com.bishaladhikary.employee_service.entity.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeResponseDTO {

	private String name;

	private String email;

	private String designation;

	private EmployeeStatus status;

}
