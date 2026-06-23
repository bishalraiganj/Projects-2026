package com.nbi.transaction_service.service;

import com.bishal.transactionService.model.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends TransactionServiceGrpc.TransactionServiceImplBase
		{

	private Logger logger = LoggerFactory.getLogger(TransactionService.class);

			@Override
			public void transfer(TransferRequest request, StreamObserver<TransferResponse> responseObserver) {
				super.transfer(request, responseObserver);
			}

			@Override
			public void getTransferStatus(TransferStatusRequest request, StreamObserver<TransferStatusResponse> responseObserver) {
				super.getTransferStatus(request, responseObserver);
			}
		}
