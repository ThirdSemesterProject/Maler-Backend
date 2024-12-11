package stud.kea.dk.malerbackend.orders.dto;

import lombok.Data;

import java.util.List;


@Data
public class OrderRequest {
    private String phoneNR;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String postNo;
    private List<Long> itemIds; // Use IDs instead of OrderItem entities
    private Long shopId;
}