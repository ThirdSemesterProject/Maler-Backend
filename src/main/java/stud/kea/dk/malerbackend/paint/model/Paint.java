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



    public Paint(String name, String url, double pris, String info, String categories,String subcategories, String brand, String shine, PaintNo paintNo, Color whiteColor) {
        super(name,url, pris, info, categories, subcategories, brand); // Kalder superklassens konstruktor
        this.shine = shine;

        this.paintNo = paintNo;
    }
}