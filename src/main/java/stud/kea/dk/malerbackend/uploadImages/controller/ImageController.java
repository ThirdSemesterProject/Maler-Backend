package stud.kea.dk.malerbackend.uploadImages.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.uploadImages.model.Image;
import stud.kea.dk.malerbackend.uploadImages.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("api/upload")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final ImageRepository imageRepository;

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

    @PatchMapping("/hero-new")
    public ResponseEntity<String> setHeroImage(@RequestBody Long imageId) {
        logger.info("Received imageId: " + imageId);

        if (!imageRepository.existsById(imageId)) {
            logger.warn("Image ID does not exist: " + imageId);
            return ResponseEntity.badRequest().body("Image ID does not exist.");
        }

        // Fjern Hero-markeringen fra det nuværende Hero-billede
        imageRepository.findAll().forEach(image -> {
            if (image.isHero()) {
                image.setHero(false);
                imageRepository.save(image);
            }
        });

        // Marker det nye billede som Hero
        Optional<Image> newHeroImage = imageRepository.findById(imageId);
        if (newHeroImage.isPresent()) {
            Image image = newHeroImage.get();
            image.setHero(true);
            imageRepository.save(image);
            logger.info("Hero image updated successfully to ID: " + imageId);
            return ResponseEntity.ok("Hero image updated successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to update Hero image.");
        }
    }

    // Endpoint til at hente det aktuelle Hero-billede
    @GetMapping("/hero")
    public ResponseEntity<Image> getHeroImage() {
        Optional<Image> heroImage = imageRepository.findAll()
                .stream()
                .filter(Image::isHero)
                .findFirst();

        if (heroImage.isPresent()) {
            logger.info("Returning Hero image with ID: " + heroImage.get().getId());
            return ResponseEntity.ok(heroImage.get());
        } else {
            logger.warn("No Hero image found in the database.");
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/images/search")
    public ResponseEntity<Image> searchImageByName(@RequestParam String name) {
        Optional<Image> image = imageRepository.findImageByName(name);
        return image.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}