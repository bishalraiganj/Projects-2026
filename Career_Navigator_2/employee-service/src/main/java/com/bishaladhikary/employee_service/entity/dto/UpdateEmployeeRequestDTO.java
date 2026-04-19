package com.bishaladhikary.employee_service.entity.dto;

import com.bishaladhikary.employee_service.entity.enums.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {


	private String name;



	private String email;


	private String designation;


	private EmployeeStatus status;

}
