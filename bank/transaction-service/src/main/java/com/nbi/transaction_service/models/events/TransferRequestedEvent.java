package com.nbi.transaction_service.models.events;


import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;


@Getter
@Setter
@SuperBuilder
@Jacksonized
public class TransferRequestedEvent  extends Event{



}
