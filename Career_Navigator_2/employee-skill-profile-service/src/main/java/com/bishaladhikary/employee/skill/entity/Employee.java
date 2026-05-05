package com.bishaladhikary.employee.skill.entity;

import com.bishaladhikary.employee.skill.entity.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


	private Long id;


	private String name;


	private String email;


	private String designation;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
}
