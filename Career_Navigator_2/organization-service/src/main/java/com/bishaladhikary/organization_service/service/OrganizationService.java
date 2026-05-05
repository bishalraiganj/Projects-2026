package com.bishaladhikary.organization_service.service;

import com.bishaladhikary.organization_service.client.EmployeeClient;
import com.bishaladhikary.organization_service.client.SkillClient;
import com.bishaladhikary.organization_service.dto.*;
import com.bishaladhikary.organization_service.entity.Department;
import com.bishaladhikary.organization_service.entity.EmployeeInfo;
import com.bishaladhikary.organization_service.entity.Role;
import com.bishaladhikary.organization_service.exception.*;

import com.bishaladhikary.organization_service.repository.DepartmentRepository;
import com.bishaladhikary.organization_service.repository.EmployeeInfoRepository;
import com.bishaladhikary.organization_service.repository.RolesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service

public class OrganizationService {


	private EmployeeClient employeeClient;
	private SkillClient skillClient;


	private EmployeeInfoRepository employeeInfoRepository;

	private RolesRepository rolesRepository;

	private DepartmentRepository departmentRepository;


	public OrganizationService(EmployeeClient employeeClient, SkillClient skillClient, EmployeeInfoRepository employeeInfoRepository, RolesRepository rolesRepository, DepartmentRepository departmentRepository) {
		this.employeeClient = employeeClient;
		this.skillClient = skillClient;
		this.employeeInfoRepository = employeeInfoRepository;
		this.rolesRepository = rolesRepository;
		this.departmentRepository = departmentRepository;
	}

	// CREATE EmployeeInfo
	public CreateRelationshipResponseDTO createRelationship(CreateRelationshipRequestDTO request)
	{
		if(!employeeClient.checkExists(request.getEmployeeId()))
		{
			throw new EmployeeNotFoundException("Employee not found by id: " + request.getEmployeeId());
		}

		if(!employeeClient.checkExists(request.getManagerId()))
		{
			throw new EmployeeNotFoundException("Manager not found by id: " + request.getManagerId());
		}

		if(!departmentRepository.existsById(request.getDepartmentId()))
		{
			throw new DepartmentNotFoundException("Department not found by id: " + request.getDepartmentId());
		}

		if(!rolesRepository.existsById(request.getRoleId()))
		{
			throw new RoleNotFoundException("Role not found by id: " + request.getRoleId());
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




	//Update Employee Info / relationship  Dynamically


	public UpdateEmployeeInfoResponseDTO updateRelationship(Long id, UpdateEmployeeInfoRequestDTO request)
	{

		EmployeeInfo fetched = employeeInfoRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found by id: " + id));


		Map<String, Object> map = request.getMap();

		for(Map.Entry<String, Object> entry : map.entrySet())
		{
			String fieldName =  entry.getKey();

			switch(fieldName)
			{
				case "employeeId" : {

					Long employeeId = Long.parseLong(entry.getValue().toString());
					if(!employeeClient.checkExists(employeeId)){
						throw new EmployeeNotFoundException("Employee not found by id: " + entry.getValue().toString());
					}
					fetched.setEmployeeId(employeeId);
				}
				break;

				case "managerId" : {

					Long managerId = Long.parseLong(entry.getValue().toString());
					if(!employeeClient.checkExists(managerId))
					{
						throw new EmployeeNotFoundException("Manager not found by id: " + entry.getValue().toString());
					}
					fetched.setManagerId(managerId);
				}
				break;

				case "departmentId" : {
					Long departmentId = Long.parseLong(entry.getValue().toString());
					if(departmentRepository.existsById(departmentId))
					{
						throw new DepartmentNotFoundException("Department not found by id: " + entry.getValue().toString());
					}
					fetched.setDepartmentId(departmentId);
				}
				break;

				case "roleId" : {

					Long roleId = Long.parseLong(entry.getValue().toString());
					if(rolesRepository.existsById(roleId))
					{
						throw new RoleNotFoundException("Role not found by id: " + entry.getValue().toString());
					}
					fetched.setRoleId(roleId);
				}
			}

		}


		EmployeeInfo saved = employeeInfoRepository.save(fetched);
		UpdateEmployeeInfoResponseDTO response = new UpdateEmployeeInfoResponseDTO();

		BeanUtils.copyProperties(saved, response);
		return response;




	}



	// ADD an existing employee to existing department
	public boolean addEmployeeToDepartment(Long employeeId, Long departmentId)
	{
		if(!employeeClient.checkExists(employeeId))
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
//		if(!departmentRepository.existsById(request.getDepartment().getId()))
//		{
//			throw new DepartmentNotFoundException("Department with id: " + request.getDepartment().getId() + " could not be found ");
//		}


		Department dept =		departmentRepository.findById(request.getDepartmentId())
					.orElseThrow(()-> new DepartmentNotFoundException("Department not found by id: " + request.getDepartmentId()));


		List<Long> skills = request.getSkills();
		if(!skills.isEmpty())
		{
			for(Long id : skills)
			{
				if(!skillClient.checkExists(id))
				{
					throw new SkillNotFoundException("Skill ID: %d could not be found ".formatted(id) );
				}
			}
		}




		Role role = new Role();
		BeanUtils.copyProperties(request, role);

		dept.addRole(role);
		Department updatedDept = departmentRepository.save(dept);
//		Role savedRole =  rolesRepository.save(role);
		CreateRoleResponseDTO response = new CreateRoleResponseDTO();

		BeanUtils.copyProperties(role,response);
		return response;

	}


	public CreateDepartmentResponseDTO addDepartment(CreateDepartmentRequestDTO request)  {

		if(departmentRepository.existsByName(request.getName()))
		{
			throw new DepartmentAlreadyExistsException(" Department Name: " + request.getName( ) + " exists already" );
		}


		if(request.getEmployees()!=null && !request.getEmployees().isEmpty())
		{
			Set<Long> employees = request.getEmployees();

			for(Long employeeId : employees)
			{
				if(!employeeClient.checkExists(employeeId))
				{
					throw new EmployeeNotFoundException("Employee not found by id: " + employeeId + "could not add to department");
				}
			}

		}


		Department department  = new Department();
		BeanUtils.copyProperties(request,department);
		departmentRepository.save(department);
		CreateDepartmentResponseDTO response = new CreateDepartmentResponseDTO();
		BeanUtils.copyProperties(department,response);
		return response;
	}






}
