package stud.kea.dk.malerbackend.orders.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.service.OrderService;

import java.util.List;

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
}
