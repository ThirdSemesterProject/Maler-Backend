package stud.kea.dk.malerbackend.customer.intiData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;

@Order(6)
@Component
public class CustomerInitData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public CustomerInitData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() == 0) {
            System.out.println("Customer-tabellen er tom. Tilføjer testdata...");

            Customer customer = new Customer("Test", "Person #1","mapo@mail.com","Stenhuggere gade 7", "2860", "52146974", "Dette firma");
            Customer customer2 = new Customer("Test", "Person #2","Phil99@mail.com","Invisible Street 14", "2587", "87964123", "Et andet firma");
            Customer customer3 = new Customer("Test", "Person #3","Al82@mail.com","Christmas Wonderland 9", "5658", "85743691", "Et tredje firma");
            customerRepository.save(customer);
            customerRepository.save(customer2);
            customerRepository.save(customer3);

            System.out.println("Testkunder er blevet tilføjet.");
        } else {
            System.out.println("Customer-tabellen er allerede udfyldt. Ingen data tilføjet.");

        }
    }
}

