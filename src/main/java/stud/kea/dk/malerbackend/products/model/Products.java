package stud.kea.dk.malerbackend.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.orders.model.Orders;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double pris;
    private String info;
    private String categories;
    private String brand;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    public Products() {
    }
}