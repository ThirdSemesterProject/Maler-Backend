package stud.kea.dk.malerbackend.customer.intiData;

import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;

@Order(5)
@Component
public class CustormerInitdata implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustormerInitdata(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      /*  // Opret 4 testkunder
        Customer customer1 = new Customer(null, "John", "Doe", "john.doe@example.com", "123 Street", "12345", "12345678", "Firma A");
        Customer customer2 = new Customer(null, "Jane", "Smith", "jane.smith@example.com", "456 Avenue", "67890", "87654321", "Firma B");
        Customer customer3 = new Customer(null, "Alice", "Johnson", "alice.johnson@example.com", "789 Road", "11223", "23456789", "Firma C");
        Customer customer4 = new Customer(null, "Bob", "Brown", "bob.brown@example.com", "321 Boulevard", "44556", "98765432", "Firma D");

        // Gem kunderne i databasen
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);

       */

        System.out.println("Testkunder er blevet tilføjet.");
    }
}

