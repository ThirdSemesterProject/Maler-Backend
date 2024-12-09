package stud.kea.dk.malerbackend.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.customer.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNr(String phoneNr);
}
