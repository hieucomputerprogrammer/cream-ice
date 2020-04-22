package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndStatus(Long userId, String status);
}