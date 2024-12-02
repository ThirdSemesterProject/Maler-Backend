package stud.kea.dk.malerbackend.UploadImages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.UploadImages.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

