package stud.kea.dk.malerbackend.UploadImages.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
}
