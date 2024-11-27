package stud.kea.dk.malerbackend.color.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import stud.kea.dk.malerbackend.color.model.Color;
import stud.kea.dk.malerbackend.color.repository.ColorRepository;

import java.util.List;
import java.util.Map;

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
        String url = "https://www.csscolorsapi.com/api/colors"; // API URL

        try {
            // Hent JSON fra API
            String jsonResponse = restTemplate.getForObject(url, String.class);

            if (jsonResponse != null) {
                // Mapper JSON til en generisk struktur for at få adgang til "colors"
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

                // Hent listen af farver fra "colors"-nøglen
                List<Color> colors = objectMapper.convertValue(responseMap.get("colors"), new TypeReference<List<Color>>() {});

                // Gem kun, hvis listen ikke er tom
                if (!colors.isEmpty()) {
                    colorRepository.saveAll(colors);
                    System.out.println(colors.size() + " farver blev hentet og gemt i databasen.");
                } else {
                    System.out.println("Ingen farver blev hentet fra API'et.");
                }
            } else {
                System.out.println("Tomt svar fra API'et.");
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Fejl ved API-anmodning: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (JsonProcessingException e) {
            System.err.println("Fejl ved parsing af JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Uventet fejl: " + e.getMessage());
        }
    }

}
