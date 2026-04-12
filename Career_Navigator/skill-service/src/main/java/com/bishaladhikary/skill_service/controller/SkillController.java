package com.bishaladhikary.skill_service.controller;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/skills")
@Tag(name="Skills API", description="Operations related to skills")
public class SkillController {


	@GetMapping(path="/health-test",version="1.0")
	@Operation(
			summary= "Health Check",
			description= "checks if skills service is running",
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
		return "Skills Service working";
	}

	@GetMapping(path="/health-test",version="2.0")
	@Operation(
			summary = "Health Check",
			description = " Checks if Skills service is running V2",
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
		return "Skills service working test V2";
	}
}
