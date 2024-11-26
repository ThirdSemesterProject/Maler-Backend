package stud.kea.dk.malerbackend.paint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.paint.model.Paint;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Long> {

}
