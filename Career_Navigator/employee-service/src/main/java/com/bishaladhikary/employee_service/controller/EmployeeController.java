package com.bishaladhikary.employee_service.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@Tag(name="Employee API", description="Operations related to employees")
public class EmployeeController {


	@GetMapping(path="/health-test",version="1.0")
	@Operation(
			summary= "Health Check",
			description= "checks if employee service is running",
			parameters= {
					@Parameter(
							name = "Accept",
							description = "Media type with version (application/json;version=1.0)",
							example = "application/json;version=1.0"
					)
			}
	)
	public String test()
	{
		return "Employee Service working";
	}

	@GetMapping(path="/health-test",version="2.0")
	@Operation(
			summary = "Health Check",
			description = " Checks if employee service is running V2",
			parameters = {
					@Parameter(
							name = "Accept",
							description = "Media type with version (application/json;version=1.0)",
							example = "application/json;version=1.0"
					)
			}
	)
	public String testV2()
	{
		return "Employee service working test V2";
	}
}
