package stud.kea.dk.malerbackend.cart.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.cart.model.Cart;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final List<Cart> cartItems = new ArrayList<>();

    // Hent alle varer i kurven
    public List<Cart> getAllCartItems() {
        return new ArrayList<>(cartItems);
    }

    // Tilføj et produkt til kurven
    public Cart addCartItem(Cart cartItem) {
        cartItems.add(cartItem);
        return cartItem;
    }

    // Fjern et produkt fra kurven baseret på produkt-ID
    public void removeCartItem(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    // Opdater mængden af et produkt
    public Cart updateCartItem(Long productId, int quantity) {
        for (Cart item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                return item;
            }
        }
        return null;
    }

    // Tøm hele kurven
    public void clearCart() {
        cartItems.clear();
    }
}