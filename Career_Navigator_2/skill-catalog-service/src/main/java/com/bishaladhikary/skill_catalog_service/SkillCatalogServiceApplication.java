package com.bishaladhikary.skill_catalog_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
		info = @Info(
				title= "Skill catalog service API",
				version = "1.0",
				description = "Endpoints for managing Skills "go
		)
)
@SpringBootApplication
public class SkillCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillCatalogServiceApplication.class, args);
	}

}
