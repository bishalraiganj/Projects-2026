package com.bishaladhikary.employee.skill.producer;

import com.bishaladhikary.employee.skill.event.EmployeeSkillSubmittedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSkillEventProducer {

	private final StreamBridge streamBridge;

	public EmployeeSkillEventProducer(StreamBridge streamBridge) {
		this.streamBridge = streamBridge;
	}

	public void publish(EmployeeSkillSubmittedEvent event) {
		streamBridge.send("employeeSkillOutput-out-0", event);
	}
}