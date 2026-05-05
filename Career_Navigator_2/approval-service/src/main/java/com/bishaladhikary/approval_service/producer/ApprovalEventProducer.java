//package com.bishaladhikary.approval_service.producer;
//
//import com.bishaladhikary.approval_service.event.ApprovalApprovedEvent;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApprovalEventProducer {
//
//	private final KafkaTemplate<String, ApprovalApprovedEvent> kafkaTemplate;
//
//	public ApprovalEventProducer(KafkaTemplate<String, ApprovalApprovedEvent> kafkaTemplate) {
//		this.kafkaTemplate = kafkaTemplate;
//	}
//
//	public void publishApproved(ApprovalApprovedEvent event) {
//		kafkaTemplate.send("approval-events", event);
//	}
//}



package com.bishaladhikary.approval_service.producer;

import com.bishaladhikary.approval_service.event.ApprovalApprovedEvent;
import com.bishaladhikary.approval_service.event.ApprovalRejectedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class ApprovalEventProducer {

	private final StreamBridge streamBridge;

	public ApprovalEventProducer(StreamBridge streamBridge) {
		this.streamBridge = streamBridge;
	}

	public void publishApproved(ApprovalApprovedEvent event) {
		streamBridge.send("approvalOutput-out-0", event);
	}

	public void publishRejected(ApprovalRejectedEvent event)
	{
		streamBridge.send("rejectedOutput-out-0",event);
	}


}