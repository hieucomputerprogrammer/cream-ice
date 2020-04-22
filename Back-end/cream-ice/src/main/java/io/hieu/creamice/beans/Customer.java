package io.hieu.creamice.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer phone;
    private String password;
    private Date date_of_birth;
    private String address;
    private String gender;
    private String avatar;
    private String status;
    private Integer num_of_login_failed;

//    @JsonIgnore // Tell Spring framework not to convert this field to JSON.
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Collection<Order> orders = new ArrayList<Order>();

    public Customer(Long id) {
        this.id = id;
    }
}