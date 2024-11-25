package stud.kea.dk.malerbackend.products.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Products {
    private String name;
    private double pris;
    private String info;
    private String categories;
    private String brand;

}
