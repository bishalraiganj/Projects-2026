package com.nbi.transaction_service.repository;

import com.nbi.transaction_service.models.entities.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent,Long> {
}
