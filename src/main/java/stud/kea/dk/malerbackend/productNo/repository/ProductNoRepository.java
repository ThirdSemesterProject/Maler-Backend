package stud.kea.dk.malerbackend.productNo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.productNo.model.ProductNo;

@Repository
public interface ProductNoRepository extends JpaRepository<ProductNo, Long> {
}
