package com.bishaladhikary.employee_service.service;

import com.bishaladhikary.employee_service.entity.Employee;
import com.bishaladhikary.employee_service.entity.dto.CreateEmployeeRequestDTO;
import com.bishaladhikary.employee_service.entity.dto.CreateEmployeeResponseDTO;
import com.bishaladhikary.employee_service.entity.dto.UpdateEmployeeRequestDTO;
import com.bishaladhikary.employee_service.entity.dto.UpdateEmployeeResponseDTO;
import com.bishaladhikary.employee_service.entity.enums.EmployeeStatus;
import com.bishaladhikary.employee_service.repository.EmployeeRepository;
import com.bishaladhikary.employee_service.exception.DBOperationFailedException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


	private EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository)
	{
		this.repository = repository;
	}

	public CreateEmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO request)
	{
			Employee employee = mapToEmployee(request);
			employee.setStatus(EmployeeStatus.ACTIVE);
			Employee savedEmployee = repository.save(employee);

			if(savedEmployee  == null)
			{
				throw new DBOperationFailedException("Database Operation Failed: XC001, Failed to save the new Employee ");
			}

			CreateEmployeeResponseDTO response = mapToCreateEmployeeResponseDTO(savedEmployee);

			return response;
	}



	public UpdateEmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeRequestDTO request)
	{
		Employee employee = repository.findById(id)
				.orElseThrow(()-> new RuntimeException("Employee Not Found by Id: " +  id));


		if(request.getName() != null) employee.setName(request.getName());
		if(request.getDesignation() != null) employee.setDesignation(request.getDesignation());
		if(request.getStatus() != null) employee.setStatus(request.getStatus());
		if(request.getEmail() != null) employee.setEmail(request.getEmail());

		Employee updatedEmployee = repository.save(employee);

		if(updatedEmployee == null)
		{
			throw new  DBOperationFailedException("Database Operation Failed Exception");
		}

		UpdateEmployeeResponseDTO response = new UpdateEmployeeResponseDTO();

		BeanUtils.copyProperties(updatedEmployee, response);
		return  response;

	}




	public Employee mapToEmployee(CreateEmployeeRequestDTO request)
	{
		Employee employee = new Employee();
		BeanUtils.copyProperties(request,employee);
		return employee;

	}

	public CreateEmployeeResponseDTO mapToCreateEmployeeResponseDTO(Employee employee)
	{
		CreateEmployeeResponseDTO response = new CreateEmployeeResponseDTO();
		BeanUtils.copyProperties(employee, response);
		return response;
	}


}
