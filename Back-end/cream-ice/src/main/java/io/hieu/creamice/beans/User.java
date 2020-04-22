package io.hieu.creamice.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private Date date_of_birth;
    private String address;
    private String phone_number;
    private String email;
    private String avatar;
    private String status;

//    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "userrole", /*Create table has name "userrole" */
            joinColumns = @JoinColumn(name = "user_id"), /* Foreign Key is user_id (User) */
            inverseJoinColumns = @JoinColumn(name = "role_id" /* the second foreign key point to primary key of Role */)
    )
    private Collection<Role> roles = new ArrayList<Role>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<Recipe> recipes = new ArrayList<Recipe>();

    public User(Long id) {
        this.id = id;
    }
}