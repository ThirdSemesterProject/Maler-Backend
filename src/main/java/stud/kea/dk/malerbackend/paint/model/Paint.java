package stud.kea.dk.malerbackend.paint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.products.model.Products;

@Entity
@Data
@NoArgsConstructor
public class Paint extends Products {
    private String color;
    private String shine;


    @ManyToOne
    @JoinColumn(name = "paint_no_id") // Kolonnenavn i Paint-tabellen
    private PaintNo paintNo;

    public Paint(String name, double pris, String info, String categories, String brand, String color, String shine, PaintNo paintNo) {
        super(name, pris, info, categories, brand); // Kalder superklassens konstruktor
        this.color = color;
        this.shine = shine;
        this.paintNo = paintNo;
    }
}
