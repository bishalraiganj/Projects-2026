package com.bishaladhikary.organization_service.service;

import com.bishaladhikary.organization_service.client.EmployeeClient;
import com.bishaladhikary.organization_service.entity.Department;
import com.bishaladhikary.organization_service.entity.EmployeeInfo;
import com.bishaladhikary.organization_service.repository.DepartmentRepository;
import com.bishaladhikary.organization_service.repository.EmployeeInfoRepository;
import com.bishaladhikary.organization_service.repository.RolesRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

	public CreateRelationshipResponseDTO createRelationship(CreateRelationshipRequestDTO request)
	{
		if(!client.checkExist(request.getEmployeeId()))
		{
			throw new EmployeeNotFoundException("Employee not found by id: " + request.getEmployeeId());
		}

		if(!client.checkExist(request.getManagerId()))
		{
			throw EmployeeNotFoundException("Manager not found by id: " + request.getManagerId());
		}

		EmployeeInfo relationship = new EmployeeInfo();

		BeanUtils.copyProperties(request, relationship);

		try {
			EmployeeInfo savedEmployeeInfo = employeeInfoRepository.save(relationship);
		}catch(Exception e)
		{
			throw new DBOperationFailedException("Failed to save employee info: " + e.getMessage());
		}
	}


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

		Department department = departmentRepository.addEmployeeToDepartment(employeeId,departmentId);
		return true;

	}


	public boolean addRoleToDepartment(Long )

}
