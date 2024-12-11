package stud.kea.dk.malerbackend.products.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stud.kea.dk.malerbackend.products.model.Products;
import stud.kea.dk.malerbackend.products.repository.ProductsRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductsServiceTest {
    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    @Test
    void createProduct() {
    }

    @Test
    void deleteProductById() {
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Products product = new Products();
        product.setName("Test Product");
        when(productsRepository.save(product)).thenReturn(product);

        // Act
        Products result = productsService.createProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productsRepository, times(1)).save(product);
    }
    @Test
    void testDeleteProductById() {
        // Act
        String result = productsService.deleteProductById(1L);

        // Assert
        assertEquals("Product with id 1 deleted", result);
        verify(productsRepository, times(1)).deleteById(1L);
    }


}