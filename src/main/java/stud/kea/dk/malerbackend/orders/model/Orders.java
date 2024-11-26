package stud.kea.dk.malerbackend.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.products.model.Products;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // One order has many products
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Products> products;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Customer customer;
    private String pickup;
    private double totalPrice;
    private LocalDate date;

    public Orders() {
    }
}
