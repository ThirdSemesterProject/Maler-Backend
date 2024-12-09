package stud.kea.dk.malerbackend.orders.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.orderItem.repository.OrderItemRepository;
import stud.kea.dk.malerbackend.orders.dto.OrderRequest;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrdersController {

    private final OrderRepository ordersRepository;
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    public OrdersController(OrderRepository ordersRepository, ShopRepository shopRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository) {
        this.ordersRepository = ordersRepository;
        this.shopRepository = shopRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        // Validate shopId
        if (request.getShopId() == null) {
            throw new IllegalArgumentException("Shop ID must not be null");
        }

        // Fetch shop and customer
        Shop shop = shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid shop ID: " + request.getShopId()));

        Customer customer = customerRepository.findByPhoneNr(request.getPhoneNR())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setFirstName(request.getFirstName());
                    newCustomer.setLastName(request.getLastName());
                    newCustomer.setEmail(request.getEmail());
                    newCustomer.setPhoneNr(request.getPhoneNR());
                    newCustomer.setAddress(request.getAddress());
                    newCustomer.setPostNo(request.getPostNo());
                    return customerRepository.save(newCustomer);
                });

        // Fetch OrderItems by IDs from the database
        List<OrderItem> items = orderItemRepository.findAllById(request.getItemIds());

        // Create and save the order
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setCustomerName(customer.getFirstName());
        order.setItems(items);
        order.setOrderDate(LocalDate.now());
        order.setShop(shop);
        order.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);
        order = ordersRepository.save(order);

        // Map to OrderResponse DTO
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setOrderDate(order.getOrderDate());
        response.setShopName(shop.getName());
        response.setOrderStatus(order.getOrderStatus().name());
        response.setItemNames(
                items.stream()
                        .map(orderItem -> orderItem.getProduct().getName())
                        .toList()
        ); // Example item name extraction

        return ResponseEntity.ok(response);
    }
}
