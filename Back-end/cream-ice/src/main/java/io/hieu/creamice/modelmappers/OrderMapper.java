package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.beans.OrderDetails;
import io.hieu.creamice.beans.Payment;
import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.CartDTO;
import io.hieu.creamice.dto.CartItemDTO;
import io.hieu.creamice.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
	public static OrderDTO orderToOrderDTO(Order order) {
		OrderDTO orderDTO;
		orderDTO = new OrderDTO(order.getId(), order.getUser().getId(), order.getPayment_option(),
				order.getPayment().getId(), order.getCreate_date(), order.getDelivery_detail(), order.getNotes(),
				order.getStatus());
		return orderDTO;
	}

	public static Order orderDTOToOrder(OrderDTO orderDTO) {
		Order order = new Order();
		order.setUser(new User(orderDTO.getUserId()));
		order.setPayment(new Payment(orderDTO.getPaymentId()));
		order.setCreate_date(orderDTO.getCreateDate());
		order.setDelivery_detail(orderDTO.getDeliveryDetail());
		order.setNotes(orderDTO.getNotes());
		order.setStatus(orderDTO.getStatus());
		return order;
	}

	public static CartDTO orderToCartDTO(Order order) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setOrderId(order.getId());
		cartDTO.setUserId(order.getUser().getId());
		List<CartItemDTO> items = new ArrayList<>();
		double total = 0;
		for (OrderDetails orderDetails : order.getOrderDetails()) {
			CartItemDTO cartItem = new CartItemDTO();
			cartItem.setProductId(orderDetails.getRecipe().getId());
			cartItem.setProductName(orderDetails.getRecipe().getTitle());
			cartItem.setQuantity(orderDetails.getQuantity());
			if (orderDetails.getPrice() == null) {
				cartItem.setPrice(orderDetails.getRecipe().getPrice());
				cartItem.setTotal(orderDetails.getRecipe().getPrice() * orderDetails.getQuantity());
			} else {
				cartItem.setPrice(orderDetails.getPrice());
				cartItem.setTotal(orderDetails.getPrice() * orderDetails.getQuantity());
			}
			total += cartItem.getTotal();
			items.add(cartItem);
		}
		cartDTO.setItems(items);
		cartDTO.setTotal(total);
		return cartDTO;
	}
}