package com.bishaladhikary.employee_service.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/webhook")
public class WebhookController {


	private final RestTemplate restTemplate =new RestTemplate();


	@PostMapping("/refresh")
	public void refresh()
	{
		//Force git pull to update config server cache before busrefresh and comparison by other services after bus event published to kafka topic then to consumers

		restTemplate.getForObject(
				"http://config-server:8888/employee-service/default",
				String.class
		);



		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>("{}",headers);

		//Trigger config-server git pull first otherwise otherwise busrefresh can happen before and thus detecting no changes

//		restTemplate.postForObject(
//				"http://config-server:8888/actuator/refresh",
//				entity,
//				String.class
//		);
//
//		try {
//			Thread.sleep(4000);
//		}catch(InterruptedException e)
//		{
//			e.printStackTrace();
//		}
		//only then , start the refreshRemoteApplication even process of creation
		// public through kafka topic and then to the consumers




		restTemplate.postForObject(
				"http://config-server:8888/actuator/busrefresh",
				entity,
				String.class
		);
	}
}
