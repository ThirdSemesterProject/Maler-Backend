package stud.kea.dk.malerbackend.search;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ProductsRepository productsRepository;

    public SearchService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<SearchDto> searchByNameOrCategory(String query) {
        List<Products> products = productsRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);

        return products.stream().map(product -> new SearchDto(
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getDescription(),
                product.getBrand(),
                product.getURL()
        )).collect(Collectors.toList());
    }
}
