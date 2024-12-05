package stud.kea.dk.malerbackend.paint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.paint.model.Paint;

import java.util.List;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Integer> {
    @Query("SELECT p FROM Paint p JOIN FETCH p.paintNo WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.paintNo.itemNo) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Paint> findByNameContainingIgnoreCaseOrPaintNo_ItemNoContainingIgnoreCase(@Param("query") String query);
}


