package stud.kea.dk.malerbackend.cart.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Long productId;
    private String name;
    private String url;
    private int quantity;
    private double price;

    public Cart(Long productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}