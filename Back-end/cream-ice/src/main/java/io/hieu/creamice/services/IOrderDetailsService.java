package io.hieu.creamice.services;

import io.hieu.creamice.dto.OrderDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailsService {
    List<OrderDetailsDTO> findAll();

    Optional<OrderDetailsDTO> findById(Long id);

    OrderDetailsDTO create(OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO update(OrderDetailsDTO orderDetailsDTO, Long id);

    Page<OrderDetailsDTO> findAll(Pageable pageable);

    void delete(Long id);
}