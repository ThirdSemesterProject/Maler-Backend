package stud.kea.dk.malerbackend.paint.model;


import lombok.*;
import stud.kea.dk.malerbackend.productNo.model.ProductNo;
import stud.kea.dk.malerbackend.products.model.Products;


@EqualsAndHashCode(callSuper = true) // For korrekt equals og hashCode ved arv
@Setter
@Getter
public class Paint extends Products {
    private long id;
    private String color;
    private String shine;
    private ProductNo productNrID;

    public Paint(String name, double pris, String info, String categories, String brand, long l, String hvid, String s, ProductNo productNo2) {
        super(name, pris, info, categories, brand);
    }
}
