package stud.kea.dk.malerbackend.shop.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop updateShop(Long id, Shop shopDetails) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(shopDetails.getName());
            shop.setAddress(shopDetails.getAddress());
            shop.setEmail(shopDetails.getEmail());
            return shopRepository.save(shop);
        }).orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }

    // This is a comment
}
