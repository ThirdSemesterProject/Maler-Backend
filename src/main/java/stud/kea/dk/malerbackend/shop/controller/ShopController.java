package stud.kea.dk.malerbackend.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.shop.dto.ShopDTO;
import stud.kea.dk.malerbackend.shop.model.Shop;
import stud.kea.dk.malerbackend.shop.service.ShopService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<ShopDTO> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        return shops.stream()
                .map(shop -> new ShopDTO(shop.getId(), shop.getName(), shop.getAddress(), shop.getEmail()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getShopById(@PathVariable Long id) {
        return shopService.getShopById(id)
                .map(shop -> new ShopDTO(shop.getId(), shop.getName(), shop.getAddress(), shop.getEmail()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Shop createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @RequestBody Shop shopDetails) {
        return ResponseEntity.ok(shopService.updateShop(id, shopDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return ResponseEntity.noContent().build();
    }
}
