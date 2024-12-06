package stud.kea.dk.malerbackend.shop.Initdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

@Component
@Order(5)
public class ShopInitData implements CommandLineRunner {

    private final ShopRepository shopRepository;

    public ShopInitData(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the database already has data
        if (shopRepository.count() == 0) {
            System.out.println("No shops found in the database. Adding initial data...");

            // Create initial shops
            Shop shop1 = new Shop(
                    "Amagerbrogade",
                    "Amagerbrogade 59, 2300 København S",
                    "amager@maling.dk"
            );
            Shop shop2 = new Shop(
                    "Nørrebro",
                    "Nørrebrogade 18 B, 2200 København N",
                    "info@maling.dk"
            );
            Shop shop3 = new Shop(
                    "Musikbyen",
                    "Mozartsvej 29, 2450 København SV",
                    "kbhsv@maling.dk"
            );
            Shop shop4 = new Shop(
                    "Frederiksberg",
                    "Godthåbsvej 66, 2000 Frederiksberg",
                    "frb@maling.dk"
            );
            Shop shop5 = new Shop(
                    "Islands Brygge",
                    "Isafjordsgade 3, 2300 København S",
                    "bryggen@maling.dk"
            );
            Shop shop6 = new Shop(
                    "Vesterbro",
                    "Vesterbrogade 47, 1620 København V",
                    "vesterbro@maling.dk"
            );

            // Save the shops to the database
            shopRepository.save(shop1);
            shopRepository.save(shop2);
            shopRepository.save(shop3);
            shopRepository.save(shop4);
            shopRepository.save(shop5);
            shopRepository.save(shop6);

            System.out.println("Initial shop data added.");
        } else {
            System.out.println("Shops already exist in the database. No data added.");
        }
    }
}
