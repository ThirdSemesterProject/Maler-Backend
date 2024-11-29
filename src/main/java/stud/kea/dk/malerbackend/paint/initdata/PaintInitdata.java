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
            PaintNo paintNo4 = new PaintNo(null, "4051012", "0,9 L");
            PaintNo paintNo5 = new PaintNo(null, "4051013", "2,7 L");
            PaintNo paintNo6 = new PaintNo(null, "4051015", "4,5 L");

            PaintNo paintNo7 = new PaintNo(null, "4100212", "0,9 L");
            PaintNo paintNo8 = new PaintNo(null, "4100213", "2,7 L");
            PaintNo paintNo9 = new PaintNo(null, "4100215", "4,5 L");

            paintNoRepository.save(paintNo1);
            paintNoRepository.save(paintNo2);
            paintNoRepository.save(paintNo3);
            paintNoRepository.save(paintNo4);
            paintNoRepository.save(paintNo5);
            paintNoRepository.save(paintNo6);

            paintNoRepository.save(paintNo7);
            paintNoRepository.save(paintNo8);
            paintNoRepository.save(paintNo9);

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
                    "http://webp.bj.dk/productFiles/405/405_OL.png",
                    199.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                     paintNoRepository.findById(1L).get(),
                    blackColor // Link til Color
            );

            Paint paint2 = new Paint(
                    "B&J 5 Vægmaling",
                    "http://webp.bj.dk/productFiles/405/405_OL.pngt",
                    249.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                     paintNoRepository.findById(2L).get(),
                    whiteColor // Link til Color
            );

            Paint paint3 = new Paint(
                    "B&J 5 Vægmaling",
                    "http://webp.bj.dk/productFiles/405/405_OL.png",
                    299.95,
                    "Fortynding: Vand, Værktøj: Pensel, Påføring: +10 °C til +25°C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    paintNoRepository.findById(3L).get(),
                    whiteColor // Link til Color
            );

            Paint paint4 = new Paint(
                    "B&J 5 Vægmaling",
                    "http://webp.bj.dk/productFiles/405/405_OL.png",
                    399.95,
                    "Fortynding: Vand, Værktøj: Rulle, Påføring: +10 °C til +25°C, Rækkeevne: 6-8 m²/ltr, Tørretid: 4-6 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    paintNoRepository.findById(4L).get(),
                    offWhiteColor // Link til Color
            );

            Paint paint5 = new Paint(
                    "B&J 5 vægmaling",
                    "http://webp.bj.dk/productFiles/405/405_OL.png",
                    349.95,
                    "Fortynding: Vand, Værktøj: Sprøjtepistol, Påføring: +10 °C til +25°C, Rækkeevne: 7-9 m²/ltr, Tørretid: 3-5 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    paintNoRepository.findById(5L).get(),
                    whiteColor // Link til Color
            );

            Paint paint6 = new Paint(
                    "B&J Vægmaling",
                    "http://webp.bj.dk/productFiles/405/405_OL.png",
                    499.95,
                    "Fortynding: Vand, Værktøj: Pensel eller rulle, Påføring: +5 °C til +25°C, Rækkeevne: 10-12 m²/ltr, Tørretid: 6-8 timer, Kulør: Brækket Hvid",
                    "Interior",
                    "B&J",
                    "5",
                    paintNoRepository.findById(6L).get(),
                    offWhiteColor // Link til Color
            );

            Paint paint7 = new Paint(
                    "B&J 10 SuperFinish Hvid 0,9 L",
                    "http://webp.bj.dk/productFiles/410/410_OL.png",
                    199.95,
                    "Fortynding: Vand, Værktøj: Pensel, rulle eller airless, Påføring: +10 °C til +25 °C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "10",
                    paintNoRepository.findById(7L).get(),
                    whiteColor
            );

            Paint paint8 = new Paint(
                    "B&J 10 SuperFinish Hvid 2,7 L",
                    "http://webp.bj.dk/productFiles/410/410_OL.png",
                    499.95,
                    "Fortynding: Vand, Værktøj: Pensel, rulle eller airless, Påføring: +10 °C til +25 °C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "10",
                    paintNoRepository.findById(8L).get(),
                    whiteColor
            );

            Paint paint9 = new Paint(
                    "B&J 10 SuperFinish Hvid 4,5 L",
                    "http://webp.bj.dk/productFiles/410/410_OL.png",
                    749.95,
                    "Fortynding: Vand, Værktøj: Pensel, rulle eller airless, Påføring: +10 °C til +25 °C, Rækkeevne: 8-10 m²/ltr, Tørretid: 2-4 timer, Kulør: Hvid",
                    "Interior",
                    "B&J",
                    "10",
                    paintNoRepository.findById(9L).get(),
                    whiteColor
            );


            // Gem Paint i databasen
            paintRepository.save(paint1);
            paintRepository.save(paint2);
            paintRepository.save(paint3);
            paintRepository.save(paint4);
            paintRepository.save(paint5);
            paintRepository.save(paint6);
            paintRepository.save(paint7);
            paintRepository.save(paint8);
            paintRepository.save(paint9);

            System.out.println("Testdata med Paint, PaintNo og Color er blevet indlæst.");
        }
    }
}
