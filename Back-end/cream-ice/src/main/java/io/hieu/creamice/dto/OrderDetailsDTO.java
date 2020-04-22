package io.hieu.creamice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO implements Serializable {
    private Long id;
    private Long orderId;
    private Long recipeId;
    private Integer quantity;
    private Double price;
    private String notes;
}