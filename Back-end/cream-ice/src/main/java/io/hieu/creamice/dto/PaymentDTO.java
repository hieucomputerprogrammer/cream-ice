package io.hieu.creamice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO implements Serializable {
    private Long id;
    private String cardType;
    private Integer cardNumber;
    private Integer cvv;
    private String nameOnCard;
    private Date expiredDate;
    private Date dateOfBirth;
}