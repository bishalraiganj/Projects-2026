package com.bishaladhikary.skill_service;




import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "Skill Service API",
				version = "v1",
				description = "API documentation for Employee Service"
		)
)
public class SkillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillServiceApplication.class, args);
	}

}
