package io.hieu.creamice.services;

import io.hieu.creamice.dto.IceCreamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IIceCreamService {
    List<IceCreamDTO> findAll();

    Optional<IceCreamDTO> findById(Long id);

    IceCreamDTO create(IceCreamDTO iceCreamDTO);

    IceCreamDTO update(IceCreamDTO iceCreamDTO, Long id);

    Page<IceCreamDTO> findAll(Pageable pageable);

    void delete(Long id);
}