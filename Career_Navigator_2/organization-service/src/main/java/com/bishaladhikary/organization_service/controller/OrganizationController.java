package com.bishaladhikary.organization_service.controller;


import com.bishaladhikary.organization_service.dto.*;
import com.bishaladhikary.organization_service.entity.Department;
import com.bishaladhikary.organization_service.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization/api/")
public class OrganizationController {


	private OrganizationService service;

	public OrganizationController(OrganizationService service){
		this.service = service;
	}

	@PostMapping("/createRelationship")
	public ResponseEntity<CreateRelationshipResponseDTO> createRelationship(@RequestBody CreateRelationshipRequestDTO request)
	{
		return ResponseEntity.ok(service.createRelationship(request));
	}


	@PutMapping("/update/{id}")
	public ResponseEntity<UpdateEmployeeInfoResponseDTO> updateRelationship(@PathVariable Long id,@RequestBody UpdateEmployeeInfoRequestDTO request)
	{
		return ResponseEntity.ok(service.updateRelationship(id, request));
	}


	@PostMapping("/addDepartment")
	public ResponseEntity<CreateDepartmentResponseDTO> createDepartment(@RequestBody CreateDepartmentRequestDTO request)
	{
		return ResponseEntity.ok(service.addDepartment(request));
	}


	@PostMapping("/addRole")
	public ResponseEntity<CreateRoleResponseDTO>  createRole(@RequestBody CreateRoleRequestDTO request)
	{
		return ResponseEntity.ok(service.addRole(request));
	}


	@PostMapping("/addEmployeeToDepartment/{employeeId}/{departmentId}")
	public ResponseEntity<Boolean> addEmployeeToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId)
	{
		return ResponseEntity.ok(service.addEmployeeToDepartment(employeeId, departmentId));
	}








}
