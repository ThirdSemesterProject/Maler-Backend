package stud.kea.dk.malerbackend.orders.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;
import stud.kea.dk.malerbackend.orders.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderService orderService;


    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
        // Hvis servicen returnerer OrderResponse
        OrderResponse response = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(response);
    }
}