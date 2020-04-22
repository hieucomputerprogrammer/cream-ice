package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Payment;
import io.hieu.creamice.dto.PaymentDTO;
import io.hieu.creamice.repositories.IPaymentRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {
    @Test
    public void find_findAll_paymentsNotFound() {
        IPaymentRepository paymentRepository = mock(IPaymentRepository.class);

        when(paymentRepository.findAll()).thenReturn(Collections.emptyList());
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(paymentRepository);

        List<PaymentDTO> payments = paymentServiceImpl.findAll();
        assertTrue(payments.isEmpty());
    }


    @Test
    public void find_findAll_paymentsFound() {
        IPaymentRepository paymentRepository = mock(IPaymentRepository.class);
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment());


        when(paymentRepository.findAll()).thenReturn(payments);
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(paymentRepository);

        List<PaymentDTO> paymentsList = paymentServiceImpl.findAll();
        assertFalse(paymentsList.isEmpty());
    }

    @Test
    public void find_findPaymentById_paymentFound() {
        final Long mockId = 1L;
        IPaymentRepository paymentRepository = mock(IPaymentRepository.class);
        Payment payment = new Payment();

        when(paymentRepository.findById(mockId)).thenReturn(Optional.of(payment));
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(paymentRepository);

        Optional<PaymentDTO> paymentDTO = paymentServiceImpl.findById(mockId);
        assertTrue(paymentDTO.isPresent());
    }

    @Test
    public void find_findPaymentById_paymentNotFound() {
        final Long mockId = 1L;
        IPaymentRepository paymentRepository = mock(IPaymentRepository.class);

        when(paymentRepository.findById(mockId)).thenReturn(Optional.empty());
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(paymentRepository);

        Optional<PaymentDTO> paymentDTO = paymentServiceImpl.findById(mockId);
        assertTrue(paymentDTO.isPresent());
    }
}