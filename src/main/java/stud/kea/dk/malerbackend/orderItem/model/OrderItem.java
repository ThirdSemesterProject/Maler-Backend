package stud.kea.dk.malerbackend.orderItem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.products.model.Products;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // Fremmednøgle til Orders
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Fremmednøgle til Products
    private Products product;

    private int quantity;

    public OrderItem() {}

    public OrderItem(Orders order, Products product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public double calculatePrice() {
        return product.getPrice() * quantity;
    }
}
