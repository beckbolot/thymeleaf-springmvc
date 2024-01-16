package com.beck.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Enter the name")
    @Size(min = 2,max = 12,message = "Name should be from 2 to 12 characters ")
    private String name;

    @NotBlank(message = "Enter the phone")
    private String phoneNumber;

    @NotBlank(message = "Enter the address")
    private String address;

    @Email(message = "Incorrect email")
    private String email;

    @NotBlank(message = "Enter the password")
    @Size(min = 4,max = 16,message = "Password must contain from 4 to 16 characters")
    private String password;

    @Column(name = "admin")
    private boolean isAdmin;

    @Column(name = "dispatcher")
    private boolean isDispatcher;

    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BasketOrder> basketOrders;

    public User() {
    }

    public User(String name, String phoneNumber, String address, String email, String password, boolean isAdmin, boolean isDispatcher) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDispatcher = isDispatcher;
    }

    public User(String name, String phoneNumber, String address, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isDispatcher=" + isDispatcher +
                '}';
    }
}
