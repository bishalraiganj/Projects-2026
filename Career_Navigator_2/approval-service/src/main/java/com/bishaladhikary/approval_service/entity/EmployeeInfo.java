package com.bishaladhikary.approval_service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {

	private Long employeeId;

	private Long managerId;

	private Long departmentId;

	private Long roleId;



}
