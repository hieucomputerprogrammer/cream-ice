package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Payment;
import io.hieu.creamice.dto.PaymentDTO;
import io.hieu.creamice.modelmappers.PaymentMapper;
import io.hieu.creamice.repositories.IPaymentRepository;
import io.hieu.creamice.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private IPaymentRepository IPaymentRepository;

    @Autowired
    public PaymentServiceImpl(IPaymentRepository IPaymentRepository) {
        this.IPaymentRepository = IPaymentRepository;
    }

    @Override
    public List<PaymentDTO> findAll() {
        List<Payment> payments = IPaymentRepository.findAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<PaymentDTO>();
        for (Payment payment : payments) {
            paymentDTOs.add(PaymentMapper.paymentToPaymentDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public Page<PaymentDTO> findAll(Pageable pageable) {
        Page<Payment> paymentsPage = IPaymentRepository.findAll(pageable);
        Page<PaymentDTO> paymentDTOsPage = paymentsPage.map(payment -> PaymentMapper.paymentToPaymentDTO(payment));
        return paymentDTOsPage;
    }

    @Override
    public Optional<PaymentDTO> findById(Long id) {
        return IPaymentRepository.findById(id)
                .map(payment -> PaymentMapper.paymentToPaymentDTO(payment));
    }

    @Override
    public PaymentDTO create(PaymentDTO paymentDTO) {
        Payment payment = PaymentMapper.paymentDTOtoPayment(paymentDTO);
        payment = IPaymentRepository.save(payment);
        return PaymentMapper.paymentToPaymentDTO(payment);
    }

    @Override
    public PaymentDTO update(PaymentDTO paymentDTO, Long id) {
        Optional<Payment> currentPayment = IPaymentRepository.findById(id);

        if (!currentPayment.isPresent()) {
            throw new RuntimeException("No payment ID " + id + " found!");
        }
        Payment payment = currentPayment.get();
        payment.setCard_type(paymentDTO.getCardType());
        payment.setCard_number(paymentDTO.getCardNumber());
        payment.setCvv(paymentDTO.getCvv());
        payment.setName_on_card(paymentDTO.getNameOnCard());
        payment.setExpired_date(paymentDTO.getExpiredDate());
        payment.setDate_of_birth(paymentDTO.getDateOfBirth());

        return PaymentMapper.paymentToPaymentDTO(payment);
    }

    @Override
    public void delete(Long id) {
        IPaymentRepository.deleteById(id);
    }
}