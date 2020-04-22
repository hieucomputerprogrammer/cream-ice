package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Role;
import io.hieu.creamice.dto.RoleDTO;
import io.hieu.creamice.repositories.IRoleRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceImplTest {
    @Test
    public void find_findAll_rolesNotFound() {
        IRoleRepository roleRepository = mock(IRoleRepository.class);

        when(roleRepository.findAll()).thenReturn(Collections.emptyList());
        RoleServiceImpl roleServiceImpl = new RoleServiceImpl(roleRepository);

        List<RoleDTO> roles = roleServiceImpl.findAll();
        assertTrue(roles.isEmpty());
    }


    @Test
    public void find_findAll_rolesFound() {
        IRoleRepository roleRepository = mock(IRoleRepository.class);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());


        when(roleRepository.findAll()).thenReturn(roles);
        RoleServiceImpl roleServiceImpl = new RoleServiceImpl(roleRepository);

        List<RoleDTO> rolesList = roleServiceImpl.findAll();
        assertFalse(rolesList.isEmpty());
    }

    @Test
    public void find_findRoleById_roleFound() {
        final Long mockId = 1L;
        IRoleRepository roleRepository = mock(IRoleRepository.class);
        Role role = new Role();

        when(roleRepository.findById(mockId)).thenReturn(Optional.of(role));
        RoleServiceImpl roleServiceImpl = new RoleServiceImpl(roleRepository);

        Optional<RoleDTO> roleDTO = roleServiceImpl.findById(mockId);
        assertTrue(roleDTO.isPresent());
    }

    @Test
    public void find_findRoleById_roleNotFound() {
        final Long mockId = 1L;
        IRoleRepository roleRepository = mock(IRoleRepository.class);

        when(roleRepository.findById(mockId)).thenReturn(Optional.empty());
        RoleServiceImpl roleServiceImpl = new RoleServiceImpl(roleRepository);

        Optional<RoleDTO> roleDTO = roleServiceImpl.findById(mockId);
        assertTrue(roleDTO.isPresent());
    }
}