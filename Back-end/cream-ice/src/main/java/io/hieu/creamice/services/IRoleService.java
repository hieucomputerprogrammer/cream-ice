package io.hieu.creamice.services;

import io.hieu.creamice.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<RoleDTO> findAll();

    Optional<RoleDTO> findById(Long id);

    RoleDTO create(RoleDTO roleDTO);

    RoleDTO update(RoleDTO roleDTO, Long id);

    Page<RoleDTO> findAll(Pageable pageable);

    void delete(Long id);
}