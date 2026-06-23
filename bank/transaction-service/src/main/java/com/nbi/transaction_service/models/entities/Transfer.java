package com.nbi.transaction_service.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
public class Transfer {

	@Id
	@Column(length = 36)
	private String id; // UUID, generated at API entry

	@Column(name = "from_account_id", nullable = false, length = 36)
	private String fromAccountId;

	@Column(name = "to_account_id", nullable = false, length = 36)
	private String toAccountId;

	@Column(nullable = false, precision = 19, scale = 4)
	private BigDecimal amount;

	@Column(nullable = false, length = 3)
	private String currency;

	@Column(nullable = false, length = 20)
	private String status;

	@Column(name = "failure_reason", length = 255)
	private String failureReason;


	//For optimistic locking , to solve the concurrent/parallel transaction
	//transfer_id being the key for kafka all events related to same transfer_id
	//will go to same partition and thus to same consumer transaction instance
	//in the correct sequence/order thus maintaining integrity
	@Version
	@Column(nullable = false)
	private Long version = 0L;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
