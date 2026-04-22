package com.bishaladhikary.organization_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employeeInfo")
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
