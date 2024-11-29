package stud.kea.dk.malerbackend.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.shop.model.Shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public Orders() {}

    public Orders(String customerName, LocalDate orderDate) {
        this.customerName = customerName;
        this.orderDate = orderDate;
    }

}