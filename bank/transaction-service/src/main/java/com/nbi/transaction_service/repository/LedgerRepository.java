package com.nbi.transaction_service.repository;

import com.nbi.transaction_service.models.entities.LedgerEntry;
import com.nbi.transaction_service.models.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends JpaRepository<LedgerEntry,Long> {


}
