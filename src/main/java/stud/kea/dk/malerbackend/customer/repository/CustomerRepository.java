package stud.kea.dk.malerbackend.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.products.model.Products;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
