package stud.kea.dk.malerbackend.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.products.model.Products;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
