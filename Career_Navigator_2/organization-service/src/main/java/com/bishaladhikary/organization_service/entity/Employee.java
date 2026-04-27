package com.bishaladhikary.organization_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Employee {


	private Long id;


	private String name;


	private String email;


	private String designation;

//	@Enumerated(EnumType.STRING)
//	private EmployeeStatus status;
}
