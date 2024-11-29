package stud.kea.dk.malerbackend.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.kea.dk.malerbackend.shop.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
