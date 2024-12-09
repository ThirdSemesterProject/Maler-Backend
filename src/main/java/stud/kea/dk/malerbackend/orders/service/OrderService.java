package stud.kea.dk.malerbackend.orders.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Orders> getOrderDetailsById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    public Orders getOrdersById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
