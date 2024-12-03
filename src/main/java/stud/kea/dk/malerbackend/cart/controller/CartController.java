package stud.kea.dk.malerbackend.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.cart.model.Cart;
import stud.kea.dk.malerbackend.cart.service.CartService;

import java.util.List;

@RequestMapping("api/cart")
@CrossOrigin
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCartItems() {
        return ResponseEntity.ok(cartService.getAllCartItems());
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCartItem(@RequestBody Cart cartItem) {
        return ResponseEntity.ok(cartService.addCartItem(cartItem));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long productId, @RequestParam int quantity) {
        Cart updatedItem = cartService.updateCartItem(productId, quantity);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long productId) {
        cartService.removeCartItem(productId);
        return ResponseEntity.ok("Item removed from cart.");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok("Cart cleared.");
    }
}