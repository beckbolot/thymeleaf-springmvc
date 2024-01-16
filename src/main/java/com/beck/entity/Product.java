package com.beck.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill the name")
    @Size(min = 2,max = 24,message = "Name should be from 2 to 24 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type should not be empty")
    private ProductTypeEnum type;

    @NotNull
    private String description;

    @Positive(message = "Price must be bigger than 0")
    private int price;

    @OneToMany(mappedBy = "product",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BasketOrder> basketOrders;

    public Product() {
    }

    public Product(Long id, String name, ProductTypeEnum type, String description, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public Product(String name, ProductTypeEnum type, String description, int price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
