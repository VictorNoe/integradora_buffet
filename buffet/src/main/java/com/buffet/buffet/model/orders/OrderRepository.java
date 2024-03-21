package com.buffet.buffet.model.orders;

import com.buffet.buffet.model.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByNumOrder(String numOrder);
    List<Order> findByStatus(Status status);
    int countOrderByStatus_StatusName(String status);
}
