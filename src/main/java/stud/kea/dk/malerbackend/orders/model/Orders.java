package stud.kea.dk.malerbackend.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.customer.model.Customer;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn()
    Products products;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    Customer customer;
    String pickup;
    double totalPrice;
    LocalDate date;

    public Orders() {

    }
}
