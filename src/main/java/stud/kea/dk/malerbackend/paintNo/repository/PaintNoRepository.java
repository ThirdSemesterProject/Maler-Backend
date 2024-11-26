package stud.kea.dk.malerbackend.paintNo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;

import java.util.List;

@Repository
public interface PaintNoRepository extends JpaRepository<PaintNo, Long> {
    List<PaintNo> findByItemNoContainingIgnoreCase(String itemNo);
}
