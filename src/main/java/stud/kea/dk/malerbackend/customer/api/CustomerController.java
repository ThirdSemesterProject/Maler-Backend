package stud.kea.dk.malerbackend.customer.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GetMapping for at hente en kunde baseret på ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PostMapping for at oprette en ny kunde
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required.");
        }
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            return ResponseEntity.badRequest().body("First name is required.");
        }

        // Gem kunden i databasen
        customerRepository.save(customer);

        return ResponseEntity.status(201).body("Customer created successfully with ID: " + customer.getId());
    }
}
