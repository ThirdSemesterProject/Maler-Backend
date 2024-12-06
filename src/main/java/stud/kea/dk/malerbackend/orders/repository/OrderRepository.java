package stud.kea.dk.malerbackend.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.orders.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
