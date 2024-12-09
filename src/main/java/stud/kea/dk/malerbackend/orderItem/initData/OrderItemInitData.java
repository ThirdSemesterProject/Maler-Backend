package stud.kea.dk.malerbackend.orderItem.initData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;
import stud.kea.dk.malerbackend.orderItem.repository.OrderItemRepository;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import java.util.List;

@Order(8)
@Component
public class OrderItemInitData implements CommandLineRunner {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;

    public OrderItemInitData(OrderItemRepository orderItemRepository, OrderRepository orderRepository, ProductsRepository productsRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (orderItemRepository.count() == 0) {
            System.out.println("OrderItems table is empty. Adding test data...");

            // Fetch existing orders and products
            List<Orders> orders = orderRepository.findAll();
            List<Products> products = productsRepository.findAll();

            if (orders.isEmpty() || products.isEmpty()) {
                System.out.println("No orders or products found. Cannot add order items.");
                return;
            }

            // Create sample order items
            OrderItem item1 = new OrderItem(orders.get(0), products.get(0), 2); // 2 units of product 0 for order 0
            OrderItem item2 = new OrderItem(orders.get(0), products.get(1), 1); // 1 unit of product 1 for order 0
            OrderItem item3 = new OrderItem(orders.get(1), products.get(2), 5); // 5 units of product 2 for order 1
            OrderItem item4 = new OrderItem(orders.get(2), products.get(0), 3); // 3 units of product 0 for order 2

            // Save order items
            orderItemRepository.saveAll(List.of(item1, item2, item3, item4));

            System.out.println("Test order items have been added.");
        } else {
            System.out.println("OrderItems table is already populated. No data added.");
        }
    }
}
