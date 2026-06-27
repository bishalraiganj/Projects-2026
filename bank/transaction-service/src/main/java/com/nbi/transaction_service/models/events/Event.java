package com.nbi.transaction_service.models.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Event {

	 String transferId;

	 String fromAccountId;

	 String toAccountId;

	 String amount;

	 String status;




}
