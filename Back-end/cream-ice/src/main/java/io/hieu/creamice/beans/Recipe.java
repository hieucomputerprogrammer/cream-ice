package io.hieu.creamice.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ice_cream_id")
    private IceCream iceCream;

    private String title;
    private String description;
    private Double price;
    private String status;
    private Integer view_count;
    private String image;
    private String details;
    private OffsetDateTime created_at;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public Recipe(Long id) {
        this.id = id;
    }
}