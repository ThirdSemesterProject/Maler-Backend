package stud.kea.dk.malerbackend.paintNo.model;

import jakarta.persistence.*;
import lombok.*;
import stud.kea.dk.malerbackend.paint.model.Paint;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaintNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ItemNo;
    private String liters; // Assuming LiterSize is a valid Enum

    @OneToMany(mappedBy = "paintNo")
    private List<Paint> paints;

}

