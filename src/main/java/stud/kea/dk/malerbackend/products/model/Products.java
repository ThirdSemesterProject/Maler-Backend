package stud.kea.dk.malerbackend.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.orders.model.Orders;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    public String URL;
    private double price;
    private String description;
    private String category;
    private String brand;

    public Products() {}

    public Products(String name, String URL, double price, String description, String category, String brand) {
        this.name = name;
        this.URL = URL;
        this.price = price;
        this.description = description;
        this.category = category;
        this.brand = brand;
    }

}
