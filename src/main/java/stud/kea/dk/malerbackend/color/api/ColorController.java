package stud.kea.dk.malerbackend.color.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.kea.dk.malerbackend.color.service.ColorService;

@RestController
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/api/colors/fetch")
    public String fetchColors() {
        colorService.fetchAndSaveColors();
        return "Colors fetched and saved successfully!";
    }
}
