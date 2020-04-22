package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.dto.OrderDTO;
import io.hieu.creamice.repositories.IOrderRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    @Test
    public void find_findAll_ordersNotFound() {
        IOrderRepository orderRepository = mock(IOrderRepository.class);

        when(orderRepository.findAll()).thenReturn(Collections.emptyList());
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository);

        List<OrderDTO> orders = orderServiceImpl.findAll();
        assertTrue(orders.isEmpty());
    }


    @Test
    public void find_findAll_ordersFound() {
        IOrderRepository orderRepository = mock(IOrderRepository.class);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());


        when(orderRepository.findAll()).thenReturn(orders);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository);

        List<OrderDTO> ordersList = orderServiceImpl.findAll();
        assertFalse(ordersList.isEmpty());
    }

    @Test
    public void find_findOrderById_orderFound() {
        final Long mockId = 1L;
        IOrderRepository orderRepository = mock(IOrderRepository.class);
        Order order = new Order();

        when(orderRepository.findById(mockId)).thenReturn(Optional.of(order));
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository);

        Optional<OrderDTO> orderDTO = orderServiceImpl.findById(mockId);
        assertTrue(orderDTO.isPresent());
    }

    @Test
    public void find_findOrderById_orderNotFound() {
        final Long mockId = 1L;
        IOrderRepository orderRepository = mock(IOrderRepository.class);

        when(orderRepository.findById(mockId)).thenReturn(Optional.empty());
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository);

        Optional<OrderDTO> orderDTO = orderServiceImpl.findById(mockId);
        assertTrue(orderDTO.isPresent());
    }
}