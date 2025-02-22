package com.jahj.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jahj.demo.model.Product;
import com.jahj.demo.service.ProductService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product(1L, "Product1", 100.0));
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Product1")));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product1", 100.0);
        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product1")));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product createdProduct = new Product(1L, "Product1", 100.0);
        when(productService.createProduct(Mockito.any(Product.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Product1\",\"price\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product1")));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product(1L, "UpdatedProduct", 150.0);
        when(productService.updateProduct(1L, updatedProduct)).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedProduct\",\"price\":150.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
