package com.bishaladhikary.organization_service.service;

import com.bishaladhikary.organization_service.client.EmployeeClient;
import com.bishaladhikary.organization_service.dto.*;
import com.bishaladhikary.organization_service.entity.Department;
import com.bishaladhikary.organization_service.entity.EmployeeInfo;
import com.bishaladhikary.organization_service.entity.Role;
import com.bishaladhikary.organization_service.exception.*;

import com.bishaladhikary.organization_service.repository.DepartmentRepository;
import com.bishaladhikary.organization_service.repository.EmployeeInfoRepository;
import com.bishaladhikary.organization_service.repository.RolesRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;



@Service
@Data
public class OrganizationService {


	private EmployeeClient client;

	private EmployeeInfoRepository employeeInfoRepository;

	private RolesRepository rolesRepository;

	private DepartmentRepository departmentRepository;


	public OrganizationService(EmployeeInfoRepository repository, DepartmentRepository departmentRepository, RolesRepository rolesRepository, EmployeeClient client)
	{
		this.client = client;
		this.departmentRepository = departmentRepository;
		this.rolesRepository = rolesRepository;
		this.employeeInfoRepository = repository;
	}



	// CREATE EmployeeInfo
	public CreateRelationshipResponseDTO createRelationship(CreateRelationshipRequestDTO request)
	{
		if(!client.checkExist(request.getEmployeeId()))
		{
			throw new EmployeeNotFoundException("Employee not found by id: " + request.getEmployeeId());
		}

		if(!client.checkExist(request.getManagerId()))
		{
			throw new EmployeeNotFoundException("Manager not found by id: " + request.getManagerId());
		}

		EmployeeInfo relationship = new EmployeeInfo();

		BeanUtils.copyProperties(request, relationship);

		EmployeeInfo savedEmployeeInfo;
		try {
			 savedEmployeeInfo = employeeInfoRepository.save(relationship);
		}catch(Exception e)
		{
			throw new DbOperationFailedException("Failed to save employee info: " + e.getMessage());
		}

		CreateRelationshipResponseDTO response = new CreateRelationshipResponseDTO();
		BeanUtils.copyProperties(savedEmployeeInfo, response);
		return response;
	}





	// ADD an existing employee to existing department
	public boolean addEmployeeToDepartment(Long employeeId, Long departmentId)
	{
		if(!client.checkExist(employeeId))
		{
			throw new EmployeeNotFoundException("Employee not found by id: " + employeeId + "could not add to department ") ;
		}

		if(!departmentRepository.existsById(departmentId))
		{
			throw new RuntimeException("Department not found by id: " + departmentId + "could not add to department");
		}

		 departmentRepository.addEmployeeToDepartment(departmentId, employeeId);
		return true;

	}


	public CreateRoleResponseDTO addRole(CreateRoleRequestDTO request)  {
		if(!departmentRepository.existsById(request.getDepartment().getId()))
		{
			throw new DepartmentNotFoundException("Department with id: " + request.getDepartment().getId() + " could not be found ");
		}

		Role role = new Role();
		BeanUtils.copyProperties(request, role);
		Role savedRole =  rolesRepository.save(role);
		CreateRoleResponseDTO response = new CreateRoleResponseDTO();
		BeanUtils.copyProperties(savedRole,response);
		return response;

	}


	public CreateDepartmentResponseDTO addDepartment(CreateDepartmentRequestDTO request)  {

		if(departmentRepository.existsByName(request.getName()))
		{
			throw new DepartmentAlreadyExistsException(" Department Name: " + request.getName( ) + " exists already" );
		}

		Department department  = new Department();
		BeanUtils.copyProperties(request,department);
		departmentRepository.save(department);
		CreateDepartmentResponseDTO response = new CreateDepartmentResponseDTO();
		BeanUtils.copyProperties(department,response);
		return response;
	}






}
