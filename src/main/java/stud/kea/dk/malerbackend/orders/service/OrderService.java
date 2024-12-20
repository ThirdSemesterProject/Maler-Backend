package stud.kea.dk.malerbackend.orders.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;
import stud.kea.dk.malerbackend.orderItem.repository.OrderItemRepository;
import stud.kea.dk.malerbackend.orders.dto.OrderRequest;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository ordersRepository;

    public OrderService(ShopRepository shopRepository, CustomerRepository customerRepository,
                        OrderItemRepository orderItemRepository, OrderRepository ordersRepository) {
        this.shopRepository = shopRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.ordersRepository = ordersRepository;
    }


    public OrderResponse createOrder(OrderRequest request) {
        validateShopId(request.getShopId());

        Shop shop = fetchShop(request.getShopId());
        Customer customer = findOrCreateCustomer(request);
        List<OrderItem> items = fetchOrderItems(request.getItemIds());

        Orders order = createAndSaveOrder(shop, customer, items);

        return mapToOrderResponse(order, shop, items);
    }

    private void validateShopId(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop ID must not be null");
        }
    }

    private Shop fetchShop(Long shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shop ID: " + shopId));
    }

    private Customer findOrCreateCustomer(OrderRequest request) {
        return customerRepository.findByPhoneNr(request.getPhoneNR())
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
    }

    private List<OrderItem> fetchOrderItems(List<Long> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        return orderItemRepository.findAllById(itemIds);
    }

    private Orders createAndSaveOrder(Shop shop, Customer customer, List<OrderItem> items) {
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setCustomerName(customer.getFirstName());
        order.setItems(items);
        order.setOrderDate(LocalDate.now());
        order.setShop(shop);
        order.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);
        return ordersRepository.save(order);
    }

    private OrderResponse mapToOrderResponse(Orders order, Shop shop, List<OrderItem> items) {
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
        );
        return response;
    }

    // Metode der opdatere ordre statussen.
    public void updateOrderStatus(Long orderId, String newStatus) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));

        try {
            Orders.OrderStatus updatedStatus = Orders.OrderStatus.valueOf(newStatus.toUpperCase());
            order.setOrderStatus(updatedStatus);
            ordersRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status provided: " + newStatus);
        }
    }

    // Method to fetch all orders and map them to OrderResponse DTOs
    public List<OrderResponse> getAllOrder() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(order -> mapToOrderResponse(order))
                .collect(Collectors.toList());
    }

    // Method to get order details by ID and return the OrderResponse DTO
    public OrderResponse getOrderDetails(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        return mapToOrderResponse(order);
    }

    // Method to fetch orders by status and return them as OrderResponse DTOs
    public List<OrderResponse> getOrderByStatus(String status) {
        List<Orders> orders = ordersRepository.findByOrderStatus(Orders.OrderStatus.valueOf(status));
        return orders.stream()
                .map(order -> mapToOrderResponse(order))
                .collect(Collectors.toList());
    }

    // Method to fetch orders by customer ID and return them as OrderResponse DTOs
    public List<OrderResponse> findOrdersByCustomerId(Long id) {
        List<Orders> orders = ordersRepository.findByCustomerId(id);  // Make sure you have this method in your repository
        return orders.stream()
                .map(order -> mapToOrderResponse(order))
                .collect(Collectors.toList());
    }

    // Method to map Orders entity to OrderResponse DTO
    private OrderResponse mapToOrderResponse(Orders order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setOrderDate(order.getOrderDate());
        response.setShopName(order.getShop().getName());
        response.setOrderStatus(order.getOrderStatus().name());
        response.setItemNames(order.getItems().stream()
                .map(orderItem -> orderItem.getProduct().getName())  // Adjust according to your OrderItem structure
                .collect(Collectors.toList()));
        return response;
    }
}

