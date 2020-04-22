package io.hieu.creamice.services;

import io.hieu.creamice.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<OrderDTO> findAll();

    Optional<OrderDTO> findById(Long id);

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO, Long id);

    Page<OrderDTO> findAll(Pageable pageable);

    void delete(Long id);
}