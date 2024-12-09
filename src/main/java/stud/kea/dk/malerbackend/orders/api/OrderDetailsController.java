package stud.kea.dk.malerbackend.orders.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;
import stud.kea.dk.malerbackend.orderItem.repository.OrderItemRepository;
import stud.kea.dk.malerbackend.orders.entity.OrderDetailsDTO;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/order-details")
public class OrderDetailsController {

    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    public OrderDetailsController(OrderService orderService, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.orderService = orderService;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long orderId) {
        Optional<Orders> orderOpt = orderService.getOrderDetailsById(orderId);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Orders order = orderOpt.get();
        Customer customer = order.getCustomer();
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order, customer, orderItems);
        return ResponseEntity.ok(orderDetailsDTO);
    }
}