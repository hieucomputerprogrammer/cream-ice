package io.hieu.creamice.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String card_type;
    private Integer card_number;
    private Integer cvv;
    private String name_on_card;
    private Date expired_date;
    private Date date_of_birth;

    public Payment(Long id) {
        this.id = id;
    }
}