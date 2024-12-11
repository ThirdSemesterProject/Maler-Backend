package stud.kea.dk.malerbackend.cart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stud.kea.dk.malerbackend.cart.model.Cart;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {


    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
    }


    @Test
    void testAddCartItem_NewItem() {
        // Arrange
        Cart cartItem = new Cart(1L, "Test Product", 2, 100.0);

        // Act
        Cart result = cartService.addCartItem(cartItem);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(2, result.getQuantity());
        assertEquals(100.0, result.getPrice());
        assertEquals(1, cartService.getAllCartItems().size());
    }

    @Test
    void testAddCartItem_UpdateExistingItem() {
        // Arrange
        Cart cartItem1 = new Cart(1L, "Test Product", 2, 100.0);
        Cart cartItem2 = new Cart(1L, "Test Product", 3, 100.0);
        cartService.addCartItem(cartItem1);

        // Act
        Cart result = cartService.addCartItem(cartItem2);

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getQuantity()); // Updated quantity
        assertEquals(1, cartService.getAllCartItems().size()); // Still 1 item
    }

    @Test
    void testRemoveCartItem() {
        // Arrange
        Cart cartItem = new Cart(1L, "Test Product", 2, 100.0);
        cartService.addCartItem(cartItem);

        // Act
        cartService.removeCartItem(1L);

        // Assert
        assertTrue(cartService.getAllCartItems().isEmpty());
    }

    @Test
    void testClearCart() {
        // Arrange
        Cart cartItem1 = new Cart(1L, "Test Product", 2, 100.0);
        Cart cartItem2 = new Cart(2L, "Another Product", 1, 50.0);
        cartService.addCartItem(cartItem1);
        cartService.addCartItem(cartItem2);

        // Act
        cartService.clearCart();

        // Assert
        assertTrue(cartService.getAllCartItems().isEmpty());
    }

    @Test
    void testUpdateCartItem() {
        // Arrange
        Cart cartItem = new Cart(1L, "Test Product", 2, 100.0);
        cartService.addCartItem(cartItem);

        // Act
        Cart updatedItem = cartService.updateCartItem(1L, 5);

        // Assert
        assertNotNull(updatedItem);
        assertEquals(5, updatedItem.getQuantity());
    }

    @Test
    void testUpdateCartItem_NonExistingItem() {
        // Act
        Cart updatedItem = cartService.updateCartItem(1L, 5);

        // Assert
        assertNull(updatedItem); // No item to update
    }
}
