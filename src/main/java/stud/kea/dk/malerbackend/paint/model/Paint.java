package stud.kea.dk.malerbackend.paint.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.kea.dk.malerbackend.color.model.Color;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.products.model.Products;

@Entity
@Data
@NoArgsConstructor
public class Paint extends Products {

    private String shine;

    @ManyToOne
    @JoinColumn(name = "paint_no_id", nullable = false) // Foreign key til PaintNo
    private PaintNo paintNo;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;



    public Paint(String name, double pris, String info, String categories, String brand, String shine, String s, PaintNo paintNo, Color whiteColor) {
        super(name, pris, info, categories, brand); // Kalder superklassens konstruktor
        this.shine = shine;
        this.paintNo = paintNo;
    }
}