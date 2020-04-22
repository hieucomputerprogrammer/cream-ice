package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Order;
import io.hieu.creamice.beans.OrderDetails;
import io.hieu.creamice.beans.Recipe;
import io.hieu.creamice.dto.OrderDetailsDTO;
import io.hieu.creamice.modelmappers.OrderDetailsMapper;
import io.hieu.creamice.repositories.IOrderDetailsRepository;
import io.hieu.creamice.services.IOrderDetailsService;
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
public class OrderDetailsServiceImpl implements IOrderDetailsService {
    @Autowired
    private IOrderDetailsRepository IOrderDetailsRepository;

    @Autowired
    public OrderDetailsServiceImpl(IOrderDetailsRepository IOrderDetailsRepository) {
        this.IOrderDetailsRepository = IOrderDetailsRepository;
    }

    @Override
    public List<OrderDetailsDTO> findAll() {
        List<OrderDetails> orderDetails = IOrderDetailsRepository.findAll();
        List<OrderDetailsDTO> orderDetailsDTO = new ArrayList<OrderDetailsDTO>();
        for (OrderDetails orderDetail : orderDetails) {
            orderDetailsDTO.add(OrderDetailsMapper.orderDetailsToOrderDetailsDTO(orderDetail));
        }
        return orderDetailsDTO;
    }

    @Override
    public Page<OrderDetailsDTO> findAll(Pageable pageable) {
        Page<OrderDetails> orderDetailsPage = IOrderDetailsRepository.findAll(pageable);
        Page<OrderDetailsDTO> orderDetailsDTOsPage = orderDetailsPage.map(orderDetails -> OrderDetailsMapper.orderDetailsToOrderDetailsDTO(orderDetails));
        return orderDetailsDTOsPage;
    }

    @Override
    public Optional<OrderDetailsDTO> findById(Long id) {
        return IOrderDetailsRepository.findById(id)
                .map(orderDetails -> OrderDetailsMapper.orderDetailsToOrderDetailsDTO(orderDetails));
    }

    @Override
    public OrderDetailsDTO create(OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = OrderDetailsMapper.orderDetailsDTOToOrderDetails(orderDetailsDTO);
        orderDetails = IOrderDetailsRepository.save(orderDetails);
        return OrderDetailsMapper.orderDetailsToOrderDetailsDTO(orderDetails);
    }

    @Override
    public OrderDetailsDTO update(OrderDetailsDTO orderDetailsDTO, Long id) {
        Optional<OrderDetails> currentOrderDetails = IOrderDetailsRepository.findById(id);

        if (!currentOrderDetails.isPresent()) {
            throw new RuntimeException("No order details ID " + id + " found!");
        }
        OrderDetails orderDetails = currentOrderDetails.get();
        orderDetails.setOrder(new Order(orderDetailsDTO.getOrderId()));
        orderDetails.setRecipe(new Recipe(orderDetailsDTO.getRecipeId()));
        orderDetails.setQuantity(orderDetailsDTO.getQuantity());
        orderDetails.setPrice(orderDetailsDTO.getPrice());
        orderDetails.setNotes(orderDetailsDTO.getNotes());

        return OrderDetailsMapper.orderDetailsToOrderDetailsDTO(orderDetails);
    }

    @Override
    public void delete(Long id) {
        IOrderDetailsRepository.deleteById(id);
    }
}