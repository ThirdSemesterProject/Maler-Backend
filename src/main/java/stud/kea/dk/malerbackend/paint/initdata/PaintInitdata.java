package stud.kea.dk.malerbackend.paint.initdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.color.model.Color;
import stud.kea.dk.malerbackend.color.repository.ColorRepository;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.repository.PaintRepository;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.paintNo.repository.PaintNoRepository;

import java.util.ArrayList;

@Component
@Order(2)
public class PaintInitdata implements CommandLineRunner {

    private final PaintRepository paintRepository;
    private final PaintNoRepository paintNoRepository;
    private final ColorRepository colorRepository;
    public PaintInitdata(PaintRepository paintRepository, PaintNoRepository paintNoRepository, ColorRepository colorRepository) {
        this.paintRepository = paintRepository;
        this.paintNoRepository = paintNoRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        // Opret Color objekter
        Color whiteColor = colorRepository.findColorByName("White");
        Color offWhiteColor = colorRepository.findColorByName("White");
        Color blackColor = colorRepository.findColorByName("Black");


        // Gem Color i databasen
        whiteColor = colorRepository.save(whiteColor);
        offWhiteColor = colorRepository.save(offWhiteColor);
        blackColor = colorRepository.save(blackColor);

        if (paintNoRepository.count() == 0) {
            System.out.println("PaintNo-tabellen er tom. Tilføjer eksemplardata...");
            PaintNo paintNo1 = new PaintNo(null, "4050212", "0,9 L");
            PaintNo paintNo2 = new PaintNo(null, "4050213", "2,7 L");
            PaintNo paintNo3 = new PaintNo(null, "4050215", "4,5 L");

            paintNoRepository.save(paintNo1);
            paintNoRepository.save(paintNo2);
            paintNoRepository.save(paintNo3);
            System.out.println("PaintNo-data tilføjet.");
        } else {
            System.out.println("PaintNo-tabellen er allerede udfyldt. Ingen data tilføjet.");
        }
        if (paintRepository.count() == 0) {
            System.out.println("Paint-tabellen er tom. Tilføjer eksemplardata...");

            // Antag, at der er mindst én Color i databasen
            Color defaultColor = colorRepository.findAll().stream().findFirst().orElse(null);

            // Opret Paint objekter og link dem til PaintNo og Color
            Paint paint1 = new Paint(
                    "B&J 5 Vægmaling",
                    199.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    "Ca. 5, mat",
                    paintNoRepository.findById(1L).get(),
                    blackColor // Link til Color
            );

            Paint paint2 = new Paint(
                    "B&J 5 Vægmaling",
                    249.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    "Ca. 5, mat",
                    paintNoRepository.findById(2L).get(),
                    whiteColor // Link til Color
            );

            Paint paint3 = new Paint(
                    "B&J 5 Vægmaling",
                    299.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    "Ca. 5, mat",
                    paintNoRepository.findById(3L).get(),
                    whiteColor // Link til Color
            );

            Paint paint4 = new Paint(
                    "B&J 10 Vægmaling",
                    399.95,
                    "Fortynding: Vand, Værktøj: Rulle, Påføring: +10 °C til +25°C, Rækkeevne: 6-8 m²/ltr, Tørretid: 4-6 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "10",
                    "Ca. 10, mat",
                    paintNoRepository.findById(2L).get(),
                    offWhiteColor // Link til Color
            );

            Paint paint5 = new Paint(
                    "B&J 15 Loftmaling",
                    349.95,
                    "Fortynding: Vand, Værktøj: Sprøjtepistol, Påføring: +10 °C til +25°C, Rækkeevne: 7-9 m²/ltr, Tørretid: 3-5 timer, Kulør: Hvid",
                    "Ceiling",
                    "B&J",
                    "15",
                    "Ca. 15, mat",
                    paintNoRepository.findById(1L).get(),
                    whiteColor // Link til Color
            );

            Paint paint6 = new Paint(
                    "B&J Udendørs Træbeskyttelse",
                    499.95,
                    "Fortynding: Vand, Værktøj: Pensel eller rulle, Påføring: +5 °C til +25°C, Rækkeevne: 10-12 m²/ltr, Tørretid: 6-8 timer, Kulør: Brækket Hvid",
                    "Exterior",
                    "B&J",
                    "5",
                    "Ca. 5, halvmat",
                    paintNoRepository.findById(1L).get(),
                    offWhiteColor // Link til Color
            );

            // Gem Paint i databasen
            paintRepository.save(paint1);
            paintRepository.save(paint2);
            paintRepository.save(paint3);
            paintRepository.save(paint4);
            paintRepository.save(paint5);
            paintRepository.save(paint6);

            System.out.println("Testdata med Paint, PaintNo og Color er blevet indlæst.");
        }
    }
}
