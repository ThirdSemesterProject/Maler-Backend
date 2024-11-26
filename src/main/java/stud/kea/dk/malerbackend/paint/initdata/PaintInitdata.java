package stud.kea.dk.malerbackend.paint.initdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.repository.PaintRepository;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.paintNo.repository.PaintNoRepository;

import java.util.ArrayList;

@Component
public class PaintInitdata implements CommandLineRunner {

    private final PaintRepository paintRepository;
    private final PaintNoRepository paintNoRepository;

    public PaintInitdata(PaintRepository paintRepository, PaintNoRepository paintNoRepository) {
        this.paintRepository = paintRepository;
        this.paintNoRepository = paintNoRepository;
    }

    @Override
    public void run(String... args) {
        // Opret PaintNo objekter
        PaintNo paintNo1 = new PaintNo(null, "4050212", "0,9 L");
        PaintNo paintNo2 = new PaintNo(null, "4050213", "2,7 L");
        PaintNo paintNo3 = new PaintNo(null, "4050215", "4,5 L");
        PaintNo paintNo4 = new PaintNo(null, "4051012", "0,9 L");
        PaintNo paintNo5 = new PaintNo(null, "4051013", "2,7 L");
        PaintNo paintNo6 = new PaintNo(null, "4051016", "9 L");

        // Gem PaintNo i databasen
        paintNo1 = paintNoRepository.save(paintNo1);
        paintNo2 = paintNoRepository.save(paintNo2);
        paintNo3 = paintNoRepository.save(paintNo3);
        paintNo4 = paintNoRepository.save(paintNo4);
        paintNo5 = paintNoRepository.save(paintNo5);
        paintNo6 = paintNoRepository.save(paintNo6);

        // Opret Paint objekter og link dem til PaintNo
        Paint paint1 = new Paint(
                "B&J 5 Vægmaling",
                199.95,
                "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                "Interior",
                "B&J",
                "Hvid",
                "Ca. 5, mat",
                paintNo1 // Link til PaintNo
        );

        Paint paint2 = new Paint(
                "B&J 5 Vægmaling",
                249.95,
                "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                "Interior",
                "B&J",
                "Hvid",
                "Ca. 5, mat",
                paintNo2 // Link til PaintNo
        );

        Paint paint3 = new Paint(
                "B&J 5 Vægmaling",
                299.95,
                "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                "Interior",
                "B&J",
                "Hvid",
                "Ca. 5, mat",
                paintNo3 // Link til PaintNo
        );

        Paint paint4 = new Paint(
                "B&J 10 Vægmaling",
                399.95,
                "Fortynding: Vand, Værktøj: Rulle, Påføring: +10 °C til +25°C, Rækkeevne: 6-8 m²/ltr, Tørretid: 4-6 timer, Kulør: Hvid",
                "Interior",
                "B&J",
                "Hvid",
                "Ca. 10, mat",
                paintNo4 // Link til PaintNo
        );

        Paint paint5 = new Paint(
                "B&J 15 Loftmaling",
                349.95,
                "Fortynding: Vand, Værktøj: Sprøjtepistol, Påføring: +10 °C til +25°C, Rækkeevne: 7-9 m²/ltr, Tørretid: 3-5 timer, Kulør: Hvid",
                "Ceiling",
                "B&J",
                "Hvid",
                "Ca. 15, mat",
                paintNo5 // Link til PaintNo
        );

        Paint paint6 = new Paint(
                "B&J Udendørs Træbeskyttelse",
                499.95,
                "Fortynding: Vand, Værktøj: Pensel eller rulle, Påføring: +5 °C til +25°C, Rækkeevne: 10-12 m²/ltr, Tørretid: 6-8 timer, Kulør: Brækket Hvid",
                "Exterior",
                "B&J",
                "Brækket Hvid",
                "Ca. 5, halvmat",
                paintNo6 // Link til PaintNo
        );

        // Gem Paint i databasen
        paintRepository.save(paint1);
        paintRepository.save(paint2);
        paintRepository.save(paint3);
        paintRepository.save(paint4);
        paintRepository.save(paint5);
        paintRepository.save(paint6);

        System.out.println("Testdata med 6 Paint og PaintNo er blevet indlæst.");
    }

}
