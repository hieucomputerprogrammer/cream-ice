package io.hieu.creamice.services;

import io.hieu.creamice.dto.CustomerDTO;
import io.hieu.creamice.dto.CustomerInputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<CustomerDTO> findAll();

    Optional<CustomerDTO> findById(Long id);

    CustomerDTO create(CustomerInputDTO customerInputDTO);

    CustomerDTO update(CustomerInputDTO customerInputDTO, Long id);

    Page<CustomerDTO> findAll(Pageable pageable);

    void delete(Long id);
}