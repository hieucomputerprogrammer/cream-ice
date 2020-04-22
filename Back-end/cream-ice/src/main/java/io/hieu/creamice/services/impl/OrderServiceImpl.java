package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.beans.Payment;
import io.hieu.creamice.beans.User;
import io.hieu.creamice.dto.OrderDTO;
import io.hieu.creamice.modelmappers.OrderMapper;
import io.hieu.creamice.repositories.IOrderRepository;
import io.hieu.creamice.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    private IOrderRepository IOrderRepository;

    @Autowired
    public OrderServiceImpl(IOrderRepository IOrderRepository) {
        this.IOrderRepository = IOrderRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = IOrderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
        for (Order order : orders) {
            orderDTOs.add(OrderMapper.orderToOrderDTO(order));
        }
        return orderDTOs;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<Order> ordersPage = IOrderRepository.findAll(pageable);
        System.out.println(ordersPage.toString());
        Page<OrderDTO> orderDTOsPage = ordersPage.map(order -> OrderMapper.orderToOrderDTO(order));
        return orderDTOsPage;
    }

    @Override
    public Optional<OrderDTO> findById(Long id) {
        return IOrderRepository.findById(id)
                .map(order -> OrderMapper.orderToOrderDTO(order));
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = OrderMapper.orderDTOToOrder(orderDTO);
        order = IOrderRepository.save(order);
        return OrderMapper.orderToOrderDTO(order);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, Long id) {
        Optional<Order> currentOrder = IOrderRepository.findById(id);

        if (!currentOrder.isPresent()) {
            throw new RuntimeException("No order ID " + id + " found!");
        }
        Order order = currentOrder.get();
        order.setUser(new User(orderDTO.getUserId()));
        order.setPayment_option(orderDTO.getPaymentOption());
        order.setPayment(new Payment(orderDTO.getPaymentId()));
        order.setCreate_date(orderDTO.getCreateDate());
        order.setDelivery_detail(orderDTO.getDeliveryDetail());
        order.setNotes(orderDTO.getNotes());
        order.setStatus(orderDTO.getStatus());

        return OrderMapper.orderToOrderDTO(order);
    }

    @Override
    public void delete(Long id) {
        IOrderRepository.deleteById(id);
    }
}