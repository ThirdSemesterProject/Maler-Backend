package stud.kea.dk.malerbackend.products.initdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

@Component
@Order(4)
public class ProductsInitdata implements CommandLineRunner {

    private final ProductsRepository productsRepository;

    public ProductsInitdata(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productsRepository.count() == 0) {
            System.out.println("Products-tabellen er tom. Tilføjer testdata...");

            // Opret produkter
            Products product1 = new Products(
                    "Stålspartel Hård 50 mm",
                    "http://webp.bj.dk/productFiles/7100XX-7303XX/710070-710073.jpg", // Replace with actual URL
                    29.95,
                    "Kitkniv i stål , Stålspartler 1\"-5\"",
                    "Værktøj",
                    "Generic"
            );

            Products product2 = new Products(
                    "Stålspartel Hård 40 mm",
                    "http://webp.bj.dk/productFiles/7100XX-7303XX/710070-710073.jpg", // Replace with actual URL
                    24.95,
                    "Kitkniv i stål , Stålspartler 1\"-5\"",
                    "Værktøj",
                    "Generic"
            );


            Products product3 = new Products(
                    "Jernspartel 5 cm",
                    "http://webp.bj.dk/productFiles/7101XX-7101XX/710121-710125.jpg", // Replace with actual URL
                    19.95,
                    "Jernspartler 4-6cm",
                    "Værktøj",
                    "Spekter"
            );

            Products product4 = new Products(
                    "Jernspartel 6 cm",
                    "http://webp.bj.dk/productFiles/7101XX-7101XX/710121-710125.jpg", // Replace with actual URL
                    24.95,
                    "Jernspartler 4-6cm",
                    "Værktøj",
                    "Spekter"
            );

            Products product5 = new Products(
                    "Jernspartel 4 cm",
                    "http://webp.bj.dk/productFiles/7101XX-7101XX/710121-710125.jpg", // Replace with actual URL
                    14.95,
                    "Jernspartler 4-6cm",
                    "Værktøj",
                    "Spekter"
            );
            Products product6 = new Products(
                    "Penselsæt 25 - 38 - 62 mm",
                    "http://webp.bj.dk/productFiles/6201XX-6508XX/620861_Frit.png", // Replace with actual URL
                    49.95,
                    "Et sæt af pensler med størrelser 25 mm, 38 mm og 62 mm. Ideel til forskellige maleropgaver.",
                    "Værktøj",
                    "Generic"
            );

            // Gem produkter i databasen
            productsRepository.save(product1);
            productsRepository.save(product2);
            productsRepository.save(product3);

            // Gem produkter i databasen
            productsRepository.save(product4);
            productsRepository.save(product5);
            productsRepository.save(product6);

            System.out.println("Produkter tilføjet til databasen.");
        } else {
            System.out.println("Products-tabellen er allerede udfyldt. Ingen data tilføjet.");

        }
    }
}
