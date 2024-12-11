package stud.kea.dk.malerbackend.orders.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stud.kea.dk.malerbackend.customer.model.Customer;
import stud.kea.dk.malerbackend.customer.repository.CustomerRepository;
import stud.kea.dk.malerbackend.orderItem.repository.OrderItemRepository;
import stud.kea.dk.malerbackend.orders.dto.OrderRequest;
import stud.kea.dk.malerbackend.orders.dto.OrderResponse;
import stud.kea.dk.malerbackend.orders.model.Orders;
import stud.kea.dk.malerbackend.orders.repository.OrderRepository;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ShopRepository shopRepository;

    @Mock
    private OrderRepository ordersRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateOrder_Success() {
        // Arrange
        OrderRequest request = new OrderRequest();
        request.setShopId(1L);
        request.setPhoneNR("12345678");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setItemIds(List.of(1L, 2L));

        Shop shop = new Shop();
        shop.setId(1L);
        shop.setName("Test Shop");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Orders order = new Orders();
        order.setId(1L);
        order.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);
        order.setCustomerName("John Doe"); // Mock customerName korrekt
        order.setShop(shop); // Mock shop korrekt

        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));
        when(customerRepository.findByPhoneNr("12345678")).thenReturn(Optional.of(customer));
        when(ordersRepository.save(any(Orders.class))).thenReturn(order);

        // Act
        OrderResponse response = orderService.createOrder(request);

        // Assert
        assertNotNull(response);
        assertEquals("John Doe", response.getCustomerName());
        assertEquals("Test Shop", response.getShopName());
        assertEquals("IGANGVÆRENDE", response.getOrderStatus());
    }


    @Test
    void testUpdateOrderStatus_Success() {
        // Arrange
        Orders order = new Orders();
        order.setId(1L);
        order.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);

        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        orderService.updateOrderStatus(1L, "AFSLUTTET");

        // Assert
        assertEquals(Orders.OrderStatus.AFSLUTTET, order.getOrderStatus());
        verify(ordersRepository, times(1)).save(order);
    }

    @Test
    void testUpdateOrderStatus_InvalidStatus() {
        // Arrange
        Orders order = new Orders();
        order.setId(1L);

        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateOrderStatus(1L, "INVALID");
        });
        assertEquals("Invalid status provided: INVALID", exception.getMessage());
    }
    @Test
    void testGetOrderDetails_Success() {
        // Arrange
        Shop shop = new Shop();
        shop.setId(1L);
        shop.setName("Test Shop");

        Orders order = new Orders();
        order.setId(1L);
        order.setOrderStatus(Orders.OrderStatus.IGANGVÆRENDE);
        order.setShop(shop);

        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        OrderResponse response = orderService.getOrderDetails(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Test Shop", response.getShopName());
        assertEquals("IGANGVÆRENDE", response.getOrderStatus());
    }





}