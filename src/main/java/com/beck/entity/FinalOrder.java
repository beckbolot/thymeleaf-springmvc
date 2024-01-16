package com.beck.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class FinalOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private User user;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Product> products;

    public FinalOrder() {
    }

    public FinalOrder(Date date, User user, List<Product> products) {
        this.date = date;
        this.user = user;
        this.products = products;
    }

    @Override
    public String toString() {
        return "FinalOrder{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
