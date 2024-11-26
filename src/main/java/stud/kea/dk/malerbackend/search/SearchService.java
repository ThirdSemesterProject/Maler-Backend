package stud.kea.dk.malerbackend.search;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.paintNo.model.PaintNo;
import stud.kea.dk.malerbackend.paintNo.repository.PaintNoRepository;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private final PaintNoRepository paintNoRepository;
    private final ProductsRepository productsRepository;

    public SearchService(PaintNoRepository paintNoRepository, ProductsRepository productsRepository) {
        this.paintNoRepository = paintNoRepository;
        this.productsRepository = productsRepository;
    }

    public Map<String, Object> searchByNameOrItemNo(String query) {
        List<PaintNo> paintNoResults = paintNoRepository.findByItemNoContainingIgnoreCase(query);
        List<Products> productResults = productsRepository.findByNameContainingIgnoreCase(query);

        // Kombiner resultaterne i en Map (eller anden struktur efter behov)
        Map<String, Object> combinedResults = new HashMap<>();
        combinedResults.put("paintNos", paintNoResults);
        combinedResults.put("products", productResults);

        return combinedResults;
    }
}
