package stud.kea.dk.malerbackend.paint.service;

import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.paint.model.Paint;
import stud.kea.dk.malerbackend.paint.repository.PaintRepository;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import java.util.List;

@Service
public class PaintService {

    private final PaintRepository paintRepository;
    private final ProductsRepository productsRepository;

    public PaintService(PaintRepository paintRepository, ProductsRepository productsRepository) {
        this.paintRepository = paintRepository;
        this.productsRepository = productsRepository;
    }

    public Paint createPaint(Paint paint) {
        return paintRepository.save(paint);
    }

    public List<Paint> getAllPaints() {
        return paintRepository.findAll();
    }
    public Paint getPaintById(Long id) {
        // Finder et produkt baseret på ID
        Products product = productsRepository.findById(id).orElse(null);
        // Tjekker, om produktet er en Paint og returnerer det som Paint
        if (product instanceof Paint) {
            return (Paint) product; // Typecast til Paint
        } else {
            return null; // Returner null, hvis produktet ikke er en Paint
        }
    }
}
