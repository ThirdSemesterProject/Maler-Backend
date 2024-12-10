package stud.kea.dk.malerbackend.orders.api;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;
import stud.kea.dk.malerbackend.orders.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all orders, now returning DTOs
    @GetMapping("/getAllOrders")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrder(); // Return the DTOs
    }

    // Get order by ID, returning DTO
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderDetails(id); // Use DTO method
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get orders by status, returning DTOs
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable String status) {
        List<OrderResponse> orderList = orderService.getOrderByStatus(status); // Return DTOs
        if (orderList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderList);
        }
    }

    // Get orders by customer ID, returning DTOs
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable Long id) {
        List<OrderResponse> orders = orderService.findOrdersByCustomerId(id); // Return DTOs
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    // Patch mapping for updating order status, no change needed here
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");

        if (newStatus == null || newStatus.isEmpty()) {
            return ResponseEntity.badRequest().body("Status cannot be empty.");
        }

        try {
            orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok("Order status updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
