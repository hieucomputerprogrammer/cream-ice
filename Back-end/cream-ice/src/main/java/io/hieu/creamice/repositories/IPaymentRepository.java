package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}