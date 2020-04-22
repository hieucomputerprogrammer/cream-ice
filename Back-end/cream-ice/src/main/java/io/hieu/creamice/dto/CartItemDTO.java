package io.hieu.creamice.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double total;
}