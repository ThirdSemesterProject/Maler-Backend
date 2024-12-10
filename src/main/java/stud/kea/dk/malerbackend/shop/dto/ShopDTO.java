package stud.kea.dk.malerbackend.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
    private long id;
    private String name;
    private String address;
    private String email;

    public ShopDTO(long id, String name, String address, String email) {
        this.id = id;
        this.name=name;
        this.address=address;
        this.email=email;
    }
}
