package stud.kea.dk.malerbackend.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.products.model.Products;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
