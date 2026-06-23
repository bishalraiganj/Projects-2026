package com.nbi.transaction_service.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ledger_entries")
@Getter
@Setter
@NoArgsConstructor
public class LedgerEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transfer_id", nullable = false, length = 36)
	private String transferId;

	@Column(name = "account_id", nullable = false, length = 36)
	private String accountId;

	@Column(name = "entry_type", nullable = false, length = 10)
	private String entryType; // "DEBIT" or "CREDIT"

	@Column(nullable = false, precision = 19, scale = 4)
	private BigDecimal amount;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
}
