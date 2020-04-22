package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.beans.OrderDetails;
import io.hieu.creamice.dto.AddCartItemDTO;
import io.hieu.creamice.dto.CartDTO;
import io.hieu.creamice.modelmappers.OrderMapper;
import io.hieu.creamice.repositories.IOrderDetailsRepository;
import io.hieu.creamice.repositories.IOrderRepository;
import io.hieu.creamice.repositories.IRecipeRepository;
import io.hieu.creamice.repositories.IUserRepository;
import io.hieu.creamice.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;

@Service
@Transactional
public class CartServiceImpl implements ICartService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Override
    public CartDTO getUserCartItems(Long userId) {
        Order order = orderRepository.findByUserIdAndStatus(userId, "Draft");
        if (order == null) {
            return emptyCart(userId);
        }

        return OrderMapper.orderToCartDTO(order);
    }

    private CartDTO emptyCart(Long userId) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(userId);
        cartDTO.setItems(Collections.emptyList());
        cartDTO.setTotal(0.0);

        return cartDTO;
    }

    @Override
    public CartDTO addCartItem(AddCartItemDTO addCartItemDTO) {
        Order order = orderRepository.findByUserIdAndStatus(addCartItemDTO.getUserId(), "Draft");
        boolean isOrderExists = order != null;
        if (!isOrderExists) {
            order = new Order();
            order.setStatus("Draft");
            order.setUser(this.userRepository.getOne(addCartItemDTO.getUserId()));
            this.orderRepository.save(order);
        }

        boolean isProductExists = false;
        for (OrderDetails orderDetail : order.getOrderDetails()) {
            if (Objects.equals((orderDetail.getRecipe().getId()), addCartItemDTO.getProductId())) {
                isProductExists = true;
                orderDetail.setQuantity(orderDetail.getQuantity() + addCartItemDTO.getQuantity());
            }
        }
        if (!isProductExists) {
            OrderDetails newOrderDetail = new OrderDetails();
            newOrderDetail.setOrder(order);
            newOrderDetail.setQuantity(addCartItemDTO.getQuantity());
            newOrderDetail.setRecipe(recipeRepository.getOne(addCartItemDTO.getProductId()));
            order.getOrderDetails().add(newOrderDetail);
        }

        return OrderMapper.orderToCartDTO(order);
    }

    @Override
    public CartDTO removeCartItem(Long orderId, Long recipeId) {
        orderDetailsRepository.deleteByOrderIdAndRecipeId(orderId, recipeId);
        Order order = orderRepository.findById(orderId).get();
        return OrderMapper.orderToCartDTO(order);
    }
}