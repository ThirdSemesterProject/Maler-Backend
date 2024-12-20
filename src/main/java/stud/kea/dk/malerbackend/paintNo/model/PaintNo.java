package stud.kea.dk.malerbackend.paintNo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "paint_no")
public class PaintNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String itemNo;

    private String liters;

    public PaintNo() {}

    public PaintNo(Long id, String itemNo, String liters) {
        this.id = id;
        this.itemNo = itemNo;
        this.liters = liters;
    }
}

