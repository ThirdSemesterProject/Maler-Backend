package stud.kea.dk.malerbackend.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stud.kea.dk.malerbackend.products.model.Products;

@Repository
public interface productsRepository extends JpaRepository<Products, Long> {
}
