package com.nbi.transaction_service.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="outbox_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "aggregate_id", nullable = false, length = 36)
	private String aggregateId; // transferId

	@Column(name = "event_type", nullable = false, length = 50)
	private String eventType; // e.g. "TransferInitiated"

	@Column(name = "payload", nullable = false, columnDefinition = "TEXT")
	private String payload; // JSON stored as text

	@Column(name = "published", nullable = false)
	private Boolean published = false;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;




}
