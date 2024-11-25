package stud.kea.dk.malerbackend.paint.initdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.productNo.model.ProductNo;
import stud.kea.dk.malerbackend.productNo.repository.ProductNoRepository;

@Component
public class PaintInitdata implements CommandLineRunner {

    private final PaintRepository paintRepository;
    private final ProductNoRepository productNoRepository;

    @Autowired
    public PaintInitdata(PaintRepository paintRepository, ProductNoRepository productNoRepository) {
        this.paintRepository = paintRepository;
        this.productNoRepository = productNoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Slet eksisterende data (valgfrit)
        paintRepository.deleteAll();
        productNoRepository.deleteAll();

        // Opret ProductNo-objekter
        ProductNo productNo1 = new ProductNo(4050212L, "0,9 L");
        ProductNo productNo2 = new ProductNo(4050213L, "2,7 L");
        ProductNo productNo3 = new ProductNo(4050215L, "4,5 L");
        ProductNo productNo4 = new ProductNo(4051012L, "0,9 L");
        ProductNo productNo5 = new ProductNo(4051013L, "2,7 L");
        ProductNo productNo6 = new ProductNo(4051015L, "4,5 L");
        ProductNo productNo7 = new ProductNo(4051016L, "9 L");

        // Gem ProductNo i databasen
        productNoRepository.save(productNo1);
        productNoRepository.save(productNo2);
        productNoRepository.save(productNo3);
        productNoRepository.save(productNo4);
        productNoRepository.save(productNo5);
        productNoRepository.save(productNo6);
        productNoRepository.save(productNo7);

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
                productNo1
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
                productNo2
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
                productNo3
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
                productNo4
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
                productNo5
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
                productNo6
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
                productNo7
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
