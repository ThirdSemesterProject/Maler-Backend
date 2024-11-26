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

    @Column(nullable = false, unique = true)
    private String itemNo;

    private String liters;

    @OneToMany(mappedBy = "paintNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paint> paints;
}