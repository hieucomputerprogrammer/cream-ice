package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Payment;
import io.hieu.creamice.dto.PaymentDTO;

public class PaymentMapper {
    public static PaymentDTO paymentToPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO;
        paymentDTO = new PaymentDTO(payment.getId(), payment.getCard_type(), payment.getCard_number(), payment.getCvv()
                , payment.getName_on_card(), payment.getExpired_date(), payment.getDate_of_birth());
        return paymentDTO;
    }

    public static Payment paymentDTOtoPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setCard_type(paymentDTO.getCardType());
        payment.setCard_number(paymentDTO.getCardNumber());
        payment.setCvv(paymentDTO.getCvv());
        payment.setName_on_card(paymentDTO.getNameOnCard());
        payment.setExpired_date(paymentDTO.getExpiredDate());
        payment.setDate_of_birth(paymentDTO.getDateOfBirth());
        return payment;
    }
}