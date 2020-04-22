package io.hieu.creamice.dto;

import lombok.Data;

@Data
public class AddCartItemDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;
}