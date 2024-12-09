package stud.kea.dk.malerbackend.orders.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;

@Order(7)
@Component
public class OrdersInitData implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;

    public OrdersInitData(OrderRepository orderRepository, CustomerRepository customerRepository, ShopRepository shopRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (orderRepository.count() == 0) {
            System.out.println("Orders table is empty. Adding test data...");

            // Fetch existing customers and shops
            List<Customer> customers = customerRepository.findAll();
            List<Shop> shops = shopRepository.findAll();

            if (customers.size() < 3 || shops.size() < 2) {
                System.out.println("Not enough customers or shops available. Cannot add orders.");
                return;
            }

            // Create sample orders
            Orders order1 = new Orders();
            order1.setCustomerName(customers.get(0).getFirstName() + " " + customers.get(0).getLastName());
            order1.setOrderDate(LocalDate.now().minusDays(5));
            order1.setOrderStatus(Orders.OrderStatus.MODTAGET);
            order1.setShop(shops.get(0));
            order1.setCustomer(customers.get(0));

            Orders order2 = new Orders();
            order2.setCustomerName(customers.get(1).getFirstName() + " " + customers.get(1).getLastName());
            order2.setOrderDate(LocalDate.now().minusDays(2));
            order2.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);
            order2.setShop(shops.get(1));
            order2.setCustomer(customers.get(1));

            Orders order3 = new Orders();
            order3.setCustomerName(customers.get(2).getFirstName() + " " + customers.get(2).getLastName());
            order3.setOrderDate(LocalDate.now());
            order3.setOrderStatus(Orders.OrderStatus.AFSLUTTET);
            order3.setShop(shops.get(0));
            order3.setCustomer(customers.get(2));

            // Save orders
            orderRepository.saveAll(List.of(order1, order2, order3));

            System.out.println("Test orders have been added.");
        } else {
            System.out.println("Orders table is already populated. No data added.");
        }
    }
}