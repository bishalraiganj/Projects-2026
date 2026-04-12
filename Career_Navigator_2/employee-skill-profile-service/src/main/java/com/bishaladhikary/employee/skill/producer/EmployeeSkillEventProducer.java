package com.bishaladhikary.employee.skill.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSkillEventProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public EmployeeSkillEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishSkillSubmitted(Object event) {
		kafkaTemplate.send("employee-skill-events", event);
	}
}