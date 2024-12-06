package stud.kea.dk.malerbackend.customer.intiData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;

@Order(5)
@Component
public class CustomerInitData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerInitData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testkunder er blevet tilføjet.");
    }
}

