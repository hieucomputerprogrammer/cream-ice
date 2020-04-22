package io.hieu.creamice.dto;

import io.hieu.creamice.beans.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String status;
    private String avatarInBase64;
    private Collection<Role> roles;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}