package stud.kea.dk.malerbackend.orderItem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
