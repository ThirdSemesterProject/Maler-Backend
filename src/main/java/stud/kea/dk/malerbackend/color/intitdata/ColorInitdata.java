package stud.kea.dk.malerbackend.color.intitdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import stud.kea.dk.malerbackend.color.model.Color;
import stud.kea.dk.malerbackend.color.repository.ColorRepository;
import stud.kea.dk.malerbackend.color.service.ColorService;

import java.util.List;
@Component
@Order(1)
public class ColorInitdata implements CommandLineRunner {
    private ColorRepository colorRepository;
    private ColorService colorService;

    ColorInitdata(ColorRepository colorRepository, ColorService colorService) {
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
