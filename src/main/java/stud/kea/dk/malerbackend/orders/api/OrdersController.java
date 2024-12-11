package stud.kea.dk.malerbackend.orders.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import stud.kea.dk.malerbackend.orders.dto.OrderRequest;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;

import stud.kea.dk.malerbackend.orders.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrdersController {

    private final OrderService orderService;
        public OrdersController(OrderService orderService) {
            this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }
}
