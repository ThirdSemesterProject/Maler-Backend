package stud.kea.dk.malerbackend.color.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.color.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
