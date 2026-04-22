package com.bishaladhikary.employee.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.bishaladhikary.employee.skill.feign")

@SpringBootApplication
public class EmployeeSkillProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSkillProfileServiceApplication.class, args);
	}

}
