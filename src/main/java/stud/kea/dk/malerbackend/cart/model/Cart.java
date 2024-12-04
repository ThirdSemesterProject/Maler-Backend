package stud.kea.dk.malerbackend.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public double getTotalPrice() {
        return price * quantity;
    }
}