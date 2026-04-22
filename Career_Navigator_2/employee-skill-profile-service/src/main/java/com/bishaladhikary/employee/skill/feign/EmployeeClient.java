package com.bishaladhikary.employee.skill.feign;

import com.bishaladhikary.employee.skill.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="employee-service")
public interface EmployeeClient {

	@GetMapping("/employee/find/{id}")
	public Employee getEmployee(@PathVariable Long id);


}
