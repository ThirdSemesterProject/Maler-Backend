package stud.kea.dk.malerbackend.products.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.service.ProductsService;

import java.util.List;

@RequestMapping("api/products")
@CrossOrigin
@RestController
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/getAllProducts")
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/getBySubcategory")
    public ResponseEntity<List<Products>> getProductsBySubcategory(@RequestParam String subcategory) {
        try {
            List<Products> products = productsService.getProductsBySubcategory(subcategory);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getPaint(@PathVariable Long id) {
        Products products = productsService.getProductById(id);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createProduct")
    public Products createProduct(@RequestBody Products products) {
        return productsService.createProduct(products);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable long id, @RequestBody Products products) {
        Products updateProducts = productsService.updateProduct(id, products);
        return ResponseEntity.ok(updateProducts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        String result = productsService.deleteProductById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
