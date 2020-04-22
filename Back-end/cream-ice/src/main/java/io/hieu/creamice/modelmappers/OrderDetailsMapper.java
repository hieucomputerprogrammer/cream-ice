package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.beans.OrderDetails;
import io.hieu.creamice.beans.Recipe;
import io.hieu.creamice.dto.OrderDetailsDTO;

public class OrderDetailsMapper {
    public static OrderDetailsDTO orderDetailsToOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderDetails.getId(), orderDetails.getOrder().getId(), orderDetails.getRecipe().getId(), orderDetails.getQuantity()
                , orderDetails.getPrice(), orderDetails.getNotes());
        return orderDetailsDTO;
    }

    public static OrderDetails orderDetailsDTOToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(orderDetailsDTO.getOrderId());
        orderDetails.setOrder(new Order(orderDetailsDTO.getOrderId()));
        orderDetails.setRecipe(new Recipe(orderDetailsDTO.getRecipeId()));
        orderDetails.setQuantity(orderDetailsDTO.getQuantity());
        orderDetails.setPrice(orderDetailsDTO.getPrice());
        orderDetailsDTO.setNotes(orderDetailsDTO.getNotes());
        return orderDetails;
    }
}