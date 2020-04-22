package io.hieu.creamice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Integer phone;
    private Date dateOfBirth;
    private String address;
    private String gender;
    private String avatar;
    private String status;
    private Integer numOfLoginFailed;
}