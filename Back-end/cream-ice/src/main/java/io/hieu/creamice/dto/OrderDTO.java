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
public class OrderDTO implements Serializable {
    private Long id;
    private Long userId;
    private String paymentOption;
    private Long paymentId;
    private Date createDate;
    private String deliveryDetail;
    private String notes;
    private String status;
}