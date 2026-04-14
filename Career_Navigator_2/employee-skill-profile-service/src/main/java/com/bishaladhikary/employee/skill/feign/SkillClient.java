package com.bishaladhikary.employee.skill.feign;


import com.bishaladhikary.employee.skill.dto.SkillResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "skill-catalog-service")
public interface SkillClient {

	@GetMapping("/skills/{id}")
	SkillResponse getSkill(@PathVariable("id") Long id);
}
