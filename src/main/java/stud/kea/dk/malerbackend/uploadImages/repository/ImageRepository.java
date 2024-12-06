package stud.kea.dk.malerbackend.uploadImages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.uploadImages.model.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageByName(String name);
    Optional<Image> findByIsHeroTrue();
}

