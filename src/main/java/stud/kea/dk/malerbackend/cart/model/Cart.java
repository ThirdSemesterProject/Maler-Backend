package stud.kea.dk.malerbackend.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}