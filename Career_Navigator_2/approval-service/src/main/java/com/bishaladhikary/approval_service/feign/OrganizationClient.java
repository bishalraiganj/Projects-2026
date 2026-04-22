package com.bishaladhikary.approval_service.feign;

import com.bishaladhikary.approval_service.entity.Employee;
import com.bishaladhikary.approval_service.entity.EmployeeInfo;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="organization-service")
public interface OrganizationClient {

	@GetMapping("/findManager/{id}")
	public EmployeeInfo findByEmployeeId(@PathVariable Long id);


}
