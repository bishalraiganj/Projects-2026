package com.bishaladhikary.organization_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {

	@Id
	private  Long employeeId;

	private  Long managerId;

	private  Long departmentId;

	private  Long roleId;
}
