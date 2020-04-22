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
public class UserInputDTO implements Serializable {
    private String username;
    private String password;
    private String name;
    private String gender;
    private Date date_of_birth;
    private String address;
    private String phone_number;
    private String email;
    private String avatarInBase64;
    private String status;
    private Collection<Role> roles;
}