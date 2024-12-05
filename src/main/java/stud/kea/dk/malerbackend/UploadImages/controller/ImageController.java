package stud.kea.dk.malerbackend.UploadImages.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.UploadImages.model.Image;
import stud.kea.dk.malerbackend.UploadImages.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/upload")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final ImageRepository imageRepository;
    private Long currentHeroImageId; // Felt til at gemme aktuelt Hero-billede-id


    // Constructor Injection
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestBody Image image) {
        if (image.getData() == null || image.getData().isEmpty()) {
            return ResponseEntity.badRequest().body("No image data received.");
        }

        imageRepository.save(image);
        return ResponseEntity.status(201).body("Image uploaded successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return ResponseEntity.ok(images);
    }

    // Endpoint til at gemme det aktuelle Hero-billede
    @PutMapping ("/hero-new")
    public ResponseEntity<String> setHeroImage(@RequestBody Long imageId) {
        if (!imageRepository.existsById(imageId)) {
            return ResponseEntity.badRequest().body("Image ID does not exist.");
        }
        currentHeroImageId = imageId;
        return ResponseEntity.ok("Hero image updated successfully.");
    }

    // Endpoint til at hente det aktuelle Hero-billede
    @GetMapping("/hero")
    public ResponseEntity<Image> getHeroImage() {
        Long fallbackHeroImageId = 1L;

        Long heroImageId = (currentHeroImageId != null) ? currentHeroImageId : fallbackHeroImageId;

        Optional<Image> image = imageRepository.findById(heroImageId);
        if (image.isPresent()) {
            logger.info("Returning Hero image with ID: " + heroImageId);
            return ResponseEntity.ok(image.get());
        } else {
            logger.warn("Hero image not found for ID: " + heroImageId);
            return ResponseEntity.status(404).body(null); // Eller returnér en fejlbesked
        }
    }

    @GetMapping("/images/search")
    public ResponseEntity<Image> searchImageByName(@RequestParam String name) {
        Optional<Image> image = imageRepository.findImageByName(name);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
