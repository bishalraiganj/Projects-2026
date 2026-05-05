package com.bishaladhikary.employee_service.controller;

import com.bishaladhikary.employee_service.entity.Employee;
import com.bishaladhikary.employee_service.entity.dto.CreateEmployeeRequestDTO;
import com.bishaladhikary.employee_service.entity.dto.CreateEmployeeResponseDTO;
import com.bishaladhikary.employee_service.entity.dto.UpdateEmployeeRequestDTO;
import com.bishaladhikary.employee_service.entity.dto.UpdateEmployeeResponseDTO;
import com.bishaladhikary.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}


	@GetMapping("/checkExists/{id}")
	public boolean checkExists(@PathVariable Long id)
	{
		return service.checkExists(id);
	}


	@PostMapping("/create")
	public ResponseEntity<CreateEmployeeResponseDTO> createEmployee(@Valid @RequestBody CreateEmployeeRequestDTO request) {
		return ResponseEntity.ok(service.createEmployee(request));
	}

	@PutMapping("/{id}/update")
	public ResponseEntity<UpdateEmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeRequestDTO request) {
		return ResponseEntity.ok(service.updateEmployee(id, request));
	}


	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id)
	{
		return ResponseEntity.ok(service.getEmployee(id));
	}


}
