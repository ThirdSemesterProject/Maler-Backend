package stud.kea.dk.malerbackend.orders.dto;

import lombok.Getter;
import lombok.Setter;
import stud.kea.dk.malerbackend.orderItem.model.OrderItem;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private long orderId;
    private String customerName;
    private LocalDate orderDate;
    private String shopName;
    private String orderStatus;
    private List<String> itemNames; // Example: List of item names
}

