package stud.kea.dk.malerbackend.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String name;
    private double pris;
    private String info;
    private String categories;
    private String brand;

    // Many products belong to one order
    @ManyToOne
    @JoinColumn(name = "order_id")
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
