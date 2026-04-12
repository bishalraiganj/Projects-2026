package com.bishaladhikary.employee.skill.controller;


import com.bishaladhikary.employee.skill.dto.AddEmployeeSkillRequest;
import com.bishaladhikary.employee.skill.dto.EmployeeSkillResponse;
import com.bishaladhikary.employee.skill.entity.EmployeeSkill;
import com.bishaladhikary.employee.skill.service.EmployeeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee-skills")
public class EmployeeSkillController{

	private final EmployeeSkillService service;

	public EmployeeSkillController(EmployeeSkillService service)
	{
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<EmployeeSkillResponse> addSkill(@RequestBody AddEmployeeSkillRequest request)
	{
		return ResponseEntity.ok(service.addSkill(request));
	}


}