package com.bishaladhikary.organization_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name= "employee-service")
public interface EmployeeClient {

	@GetMapping("/employee/checkExists/{id}")
	 boolean checkExists(@PathVariable("id") Long id);
}
