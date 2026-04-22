package com.bishaladhikary.employee.skill.entity;

import com.bishaladhikary.employee_service.entity.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;


	private String name;


	private String email;


	private String designation;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
}
