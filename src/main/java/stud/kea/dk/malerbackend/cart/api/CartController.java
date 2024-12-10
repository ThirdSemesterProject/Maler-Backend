package stud.kea.dk.malerbackend.cart.api;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addCartItem(@RequestBody Cart cartItem) {
        if (cartItem == null || cartItem.getProductId() == null) {
            return ResponseEntity.badRequest().body("Invalid cart item.");
        }
        try {
            Cart addedItem = cartService.addCartItem(cartItem);
            return ResponseEntity.ok(addedItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding item to cart.");
        }
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