package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Role;
import io.hieu.creamice.dto.RoleDTO;
import io.hieu.creamice.modelmappers.RoleMapper;
import io.hieu.creamice.repositories.IRoleRepository;
import io.hieu.creamice.services.IRoleService;
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
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository IRoleRepository) {
        this.IRoleRepository = IRoleRepository;
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = IRoleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
        for (Role role : roles) {
            roleDTOs.add(RoleMapper.roleToRoleDTO(role));
        }
        return roleDTOs;
    }

    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Role> rolesPage = IRoleRepository.findAll(pageable);
        Page<RoleDTO> roleDTOsPage = rolesPage.map(role -> RoleMapper.roleToRoleDTO(role));
        return roleDTOsPage;
    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        return IRoleRepository.findById(id)
                .map(role -> RoleMapper.roleToRoleDTO(role));
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Role role = RoleMapper.roleDTOToRole(roleDTO);
        role = IRoleRepository.save(role);
        return RoleMapper.roleToRoleDTO(role);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO, Long id) {
        Optional<Role> currentRole = IRoleRepository.findById(id);

        if (!currentRole.isPresent()) {
            throw new RuntimeException("No role ID " + id + " found!");
        }
        Role role = currentRole.get();
        role.setRole(roleDTO.getRole());

        return RoleMapper.roleToRoleDTO(role);
    }

    @Override
    public void delete(Long id) {
        IRoleRepository.deleteById(id);
    }
}