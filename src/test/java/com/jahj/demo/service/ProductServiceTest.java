package com.jahj.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.jahj.demo.exception.ResourceNotFoundException;
import com.jahj.demo.model.Product;
import com.jahj.demo.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(new Product(1L, "Product1", 100.0));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getName());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Product1", 100.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);
        assertNotNull(result);
        assertEquals("Product1", result.getName());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "Product1", 100.0);
        Product savedProduct = new Product(1L, "Product1", 100.0);
        when(productRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.createProduct(product);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("Product1", result.getName());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Product1", 100.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }
}
