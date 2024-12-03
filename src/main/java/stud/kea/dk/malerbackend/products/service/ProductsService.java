package stud.kea.dk.malerbackend.products.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products createProduct(Products products) {
        return productsRepository.save(products);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Products> getProductsBySubcategory(String subcategory) {
        return productsRepository.findBySubcategory(subcategory);
    }

    public Products getProductById(long id) {
        return productsRepository.findById(id).orElse(null);
    }

    public Products updateProduct(long id, Products products) {
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isPresent()) {
            Products productToUpdate = productsOptional.get();
            productToUpdate.setName(products.getName());
            productToUpdate.setDescription(products.getDescription());
            productToUpdate.setPrice(products.getPrice());
            productToUpdate.setCategory(products.getCategory());
            productToUpdate.setSubcategory(products.getSubcategory());
            productToUpdate.setBrand(products.getBrand());
            return productsRepository.save(productToUpdate);
        } else {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
    }

    public String deleteProductById(long id) {
        productsRepository.deleteById(id);
        return "Product with id " + id + " deleted";
    }
}
