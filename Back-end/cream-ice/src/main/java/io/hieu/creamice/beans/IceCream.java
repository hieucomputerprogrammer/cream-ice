package io.hieu.creamice.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "icecream")
public class IceCream implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "iceCream", fetch = FetchType.LAZY)
    private Collection<Recipe> recipes = new ArrayList<>();

    public IceCream(Long id) {
        this.id = id;
    }
}