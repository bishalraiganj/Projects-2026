package com.bishaladhikary.employee_service.entity.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequestDTO {



	@NotBlank(message= "Name cannot be null")
	private String name;

	@Email(message = "Email format invalid")
	@NotBlank(message= "Email cannot be blank")
	private String email;

	@NotBlank(message="Designation cannot be blank")
	private String designation;
}
