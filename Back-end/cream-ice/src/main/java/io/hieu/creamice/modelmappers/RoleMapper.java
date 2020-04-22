package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Role;
import io.hieu.creamice.dto.RoleDTO;

public class RoleMapper {
    public static RoleDTO roleToRoleDTO(Role role) {
        RoleDTO roleDTO;
        roleDTO = new RoleDTO(role.getId(), role.getRole());
        return roleDTO;
    }

    public static Role roleDTOToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRole(roleDTO.getRole());
        return role;
    }
}