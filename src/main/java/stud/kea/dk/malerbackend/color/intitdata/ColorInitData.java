package stud.kea.dk.malerbackend.color.intitdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import stud.kea.dk.malerbackend.color.repository.ColorRepository;
import stud.kea.dk.malerbackend.color.service.ColorService;

@Component
@Order(1)
public class ColorInitData implements CommandLineRunner {

    private final ColorRepository colorRepository;
    private final ColorService colorService;

    ColorInitData(ColorRepository colorRepository, ColorService colorService) {
        this.colorRepository = colorRepository;
        this.colorService = colorService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (colorRepository.count() == 0) {
            System.out.println("Color-tabellen er tom. Henter data fra ekstern API...");
            colorService.fetchAndSaveColors();
        }
    }
}
