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
@Inheritance(strategy = InheritanceType.JOINED)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private double pris;
    private String info;
    private String categories;
    private String brand;

    @ManyToOne
    @JoinColumn(name = "order_id") // Foreign key til Orders
    private Orders order;

    public Products() {
    }

    public Products(String name, double pris, String info, String categories, String brand) {
        this.name = name;
        this.pris = pris;
        this.info = info;
        this.categories = categories;
        this.brand = brand;
    }
}