package stud.kea.dk.malerbackend.customer.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.customer.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
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


    @GetMapping("/GetAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/CustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomersById(id);
        if (customer!= null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
