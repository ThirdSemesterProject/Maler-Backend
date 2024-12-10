package stud.kea.dk.malerbackend.orders.api;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.orders.model.Orders;
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

    // En GetMapping der henter alle ordre
    @GetMapping("/getAllOrders")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrder();
    }

    // En GetMapping der henter ordre ud fra ID
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders orders = orderService.getOrdersById(id);
        if (orders!= null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // En GetMapping der henter ordre ud fra status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Orders>> getOrdersByStatus(@PathVariable String status) {
        List<Orders> orderList = orderService.getOrderByStatus(status);
        if (orderList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderList);
        }
    }

    // En Get Mapping der henter kunder ud fra deres Id
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Orders>> getOrdersByCustomer(@PathVariable Long id) {
        List<Orders> orders = orderService.findOrdersByCustomerId(id);
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    // En Patch Mapping til at redigere statussen på ordre i frontend.
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
