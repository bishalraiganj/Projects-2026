package com.nbi.transaction_service.service;

import com.bishal.transactionService.model.*;
import com.nbi.transaction_service.models.exception.TransferInitiationFailed;
import com.nbi.transaction_service.models.entities.OutboxEvent;
import com.nbi.transaction_service.models.entities.Transfer;
import com.nbi.transaction_service.repository.LedgerRepository;
import com.nbi.transaction_service.repository.OutboxEventRepository;
import com.nbi.transaction_service.repository.TransferRepository;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService extends TransactionServiceGrpc.TransactionServiceImplBase
		{


			private static final Logger log =  LoggerFactory.getLogger(TransactionService.class);

			private TransferRepository transferRepository;
			private LedgerRepository ledgerRepository;
			private OutboxEventRepository outboxEventRepository;


			private OutboxRowPersistService outboxRowPersistService;


			public TransactionService(TransferRepository transferRepository, LedgerRepository ledgerRepository, OutboxEventRepository outboxEventRepository, OutboxRowPersistService outboxRowPersistService) {
				this.transferRepository = transferRepository;
				this.ledgerRepository = ledgerRepository;
				this.outboxEventRepository = outboxEventRepository;
				this.outboxRowPersistService = outboxRowPersistService;
			}

			private Logger logger = LoggerFactory.getLogger(TransactionService.class);

			@Override
			@Transactional
			public void transfer(TransferRequest request, StreamObserver<TransferResponse> responseObserver) {
				Transfer transfer = new Transfer();
				transfer.setId(UUID.randomUUID().toString());
				transfer.setFromAccountId(request.getFromAccountId());
				transfer.setToAccountId(request.getToAccountId());
				transfer.setAmount(new BigDecimal(request.getAmount()));
				transfer.setCurrency("INR");
				transfer.setStatus("TRANSFER_INITIATED");
				Transfer saved;
				try{
				 	saved = 	transferRepository.save(transfer);

				}catch(Exception e)
				{
					log.error("Error while saving transfer",e);
					throw new TransferInitiationFailed("Error while saving transfer");
				}

				OutboxEvent event = new OutboxEvent();

				event.setAggregateId(saved.getId());
				event.setPublished(false);
				event.setEventType("TRANSFER_INITIATED");
				event.setCreatedAt(LocalDateTime.now());


				String responseTransferId = outboxRowPersistService.publishTransferInitiated(event,saved);

				TransferResponse response = TransferResponse.newBuilder().setTransferId(responseTransferId).build();

				responseObserver.onNext(response);
				responseObserver.onCompleted();










			}

			@Override
			public void getTransferStatus(TransferStatusRequest request, StreamObserver<TransferStatusResponse> responseObserver) {
				super.getTransferStatus(request, responseObserver);
			}





		}
