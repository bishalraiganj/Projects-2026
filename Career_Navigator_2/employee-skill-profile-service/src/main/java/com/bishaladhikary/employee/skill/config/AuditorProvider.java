package com.bishaladhikary.employee.skill.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorProvider  implements AuditorAware<String> {


	@Override
	public Optional<String> getCurrentAuditor()
	{
		return Optional.of("default-BISHAL_ADHIKARY");
	}
}
