package io.hieu.creamice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long orderId;
    private Long userId;
    private List<CartItemDTO> items;
    private Double total;
}