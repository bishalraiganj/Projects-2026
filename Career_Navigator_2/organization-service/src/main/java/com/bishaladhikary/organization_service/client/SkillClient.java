package com.bishaladhikary.organization_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "skill-catalog-service")
public interface SkillClient {

	@GetMapping("/checkExists/{id}")
	public boolean checkExists(@PathVariable("id") Long id);

}
