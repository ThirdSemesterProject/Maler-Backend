package stud.kea.dk.malerbackend.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
