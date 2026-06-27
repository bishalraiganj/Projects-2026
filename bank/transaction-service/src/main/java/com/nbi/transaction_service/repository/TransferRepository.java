package com.nbi.transaction_service.repository;

import com.nbi.transaction_service.models.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,String> {


}
