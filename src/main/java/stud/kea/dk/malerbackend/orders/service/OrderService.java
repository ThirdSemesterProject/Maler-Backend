package stud.kea.dk.malerbackend.orders.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    // Constructor injection
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Metode der henter ordre detaljer ud fra ID
    public Optional<Orders> getOrderDetailsById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Metode der henter alle ordre
    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    // Metode der henter alle ordre ud fr ID
    public Orders getOrdersById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Metode der henter alle ordre ud fra dens status
    public List<Orders> getOrderByStatus(String status) {
        return orderRepository.findByOrderStatus(Orders.OrderStatus.valueOf(status)); // Use Orders.OrderStatus
    }
}
