package stud.kea.dk.malerbackend.color.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stud.kea.dk.malerbackend.color.model.Color;
import stud.kea.dk.malerbackend.color.repository.ColorRepository;

import java.util.List;

@Service
public class ColorService {

    private final ColorRepository colorRepository;
    private final RestTemplate restTemplate;

    // Konstruktør til afhængighedsinjektion
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
        this.restTemplate = new RestTemplate(); // Initialisering her, da det ikke er en afhængighed
    }

    public void fetchAndSaveColors() {
        String url = "https://www.csscolorsapi.com/api/colors"; // Udskift med API-URL

        try {
            // Hent JSON fra API
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // Mapper JSON til liste af farver
            ObjectMapper objectMapper = new ObjectMapper();
            List<Color> colors = objectMapper.readValue(jsonResponse, new TypeReference<>() {
            });

            // Gem farverne i databasen
            colorRepository.saveAll(colors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
