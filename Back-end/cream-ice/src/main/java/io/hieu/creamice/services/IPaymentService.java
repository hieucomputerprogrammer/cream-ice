package io.hieu.creamice.services;

import io.hieu.creamice.dto.PaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    List<PaymentDTO> findAll();

    Optional<PaymentDTO> findById(Long id);

    PaymentDTO create(PaymentDTO paymentDTO);

    PaymentDTO update(PaymentDTO paymentDTO, Long id);

    Page<PaymentDTO> findAll(Pageable pageable);

    void delete(Long id);
}