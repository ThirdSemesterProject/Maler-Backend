package stud.kea.dk.malerbackend.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.orders.model.Orders;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Query metode der finder ordre ud fra status
    List<Orders> findByCustomerId(Long customerId);
    List<Orders> findByOrderStatus(Orders.OrderStatus status);

}
