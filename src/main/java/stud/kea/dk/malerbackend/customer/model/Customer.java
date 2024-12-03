package stud.kea.dk.malerbackend.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.orders.model.Orders;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String firstName;
    String lastName;

    @Column(nullable = false, unique = true)
    String email;

    String address;
    String postNo;
    String phoneNr;
    String firma;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>(); // Tilknytning til ordrer

    public Customer() {
    }
}