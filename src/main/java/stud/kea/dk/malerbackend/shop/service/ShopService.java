package stud.kea.dk.malerbackend.shop.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.shop.dto.ShopDTO;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.repository.ShopRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ShopDTO> getAllShopDTOs() {
        return getAllShops().stream()
                .map(shop -> new ShopDTO(shop.getId(), shop.getName(), shop.getAddress(), shop.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<ShopDTO> getShopDTOById(Long id) {
        return getShopById(id)
                .map(shop -> new ShopDTO(shop.getId(), shop.getName(), shop.getAddress(), shop.getEmail()));
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
}
