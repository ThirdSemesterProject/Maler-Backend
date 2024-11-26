package stud.kea.dk.malerbackend.paint.initdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.paintNo.repository.PaintNoRepository;

@Component
public class PaintInitdata implements CommandLineRunner {

    private final PaintRepository paintRepository;
    private final PaintNoRepository paintNoRepository;

    @Autowired
    public PaintInitdata(PaintRepository paintRepository, PaintNoRepository paintNoRepository) {
        this.paintRepository = paintRepository;
        this.paintNoRepository = paintNoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Slet eksisterende data (valgfrit)
        paintRepository.deleteAll();
        paintNoRepository.deleteAll();

        // Opret ProductNo-objekter
        PaintNo paintNo1 = new PaintNo(4050212L, "0,9 L");
        PaintNo paintNo2 = new PaintNo(4050213L, "2,7 L");
        PaintNo paintNo3 = new PaintNo(4050215L, "4,5 L");
        PaintNo paintNo4 = new PaintNo(4051012L, "0,9 L");
        PaintNo paintNo5 = new PaintNo(4051013L, "2,7 L");
        PaintNo paintNo6 = new PaintNo(4051015L, "4,5 L");
        PaintNo paintNo7 = new PaintNo(4051016L, "9 L");

        // Gem ProductNo i databasen
        paintNoRepository.save(paintNo1);
        paintNoRepository.save(paintNo2);
        paintNoRepository.save(paintNo3);
        paintNoRepository.save(paintNo4);
        paintNoRepository.save(paintNo5);
        paintNoRepository.save(paintNo6);
        paintNoRepository.save(paintNo7);

        // Opret Paint-objekter og forbind med ProductNo
        Paint paint1 = new Paint(
                "B&J 5 Vægmaling 0,9 L",
                149.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4050212L,
                "Hvid",
                "5 Strå",
                paintNo1
        );

        Paint paint2 = new Paint(
                "B&J 5 Vægmaling 2,7 L",
                349.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4050213L,
                "Hvid",
                "5 Strå",
                paintNo2
        );

        Paint paint3 = new Paint(
                "B&J 5 Vægmaling 4,5 L",
                499.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4050215L,
                "Hvid",
                "5 Strå",
                paintNo3
        );

        Paint paint4 = new Paint(
                "B&J 5 Vægmaling 0,9 L",
                149.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4051012L,
                "Hvid",
                "5 Strå",
                paintNo4
        );

        Paint paint5 = new Paint(
                "B&J 5 Vægmaling 2,7 L",
                349.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4051013L,
                "Hvid",
                "5 Strå",
                paintNo5
        );

        Paint paint6 = new Paint(
                "B&J 5 Vægmaling 4,5 L",
                499.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4051015L,
                "Hvid",
                "5 Strå",
                paintNo6
        );

        Paint paint7 = new Paint(
                "B&J 5 Vægmaling 9 L",
                999.99,
                "Fortynding: Vand, Værktøj: Pensel, rulle eller airless. Påføring: +10 °C til +25°C. Rækkeevne: 8-10 m²/ltr. Tørretid: 2-4 timer.",
                "Indoor",
                "B&J",
                4051016L,
                "Hvid",
                "5 Strå",
                paintNo7
        );

        // Gem Paint i databasen
        paintRepository.save(paint1);
        paintRepository.save(paint2);
        paintRepository.save(paint3);
        paintRepository.save(paint4);
        paintRepository.save(paint5);
        paintRepository.save(paint6);
        paintRepository.save(paint7);

        System.out.println("Alle 7 Paint- og ProductNo-produkter er initialiseret.");
    }
}
