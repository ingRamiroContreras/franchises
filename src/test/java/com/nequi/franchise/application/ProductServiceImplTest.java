package com.nequi.franchise.application;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductSuccess() {
        Product product = Product.builder()
                .id("prod-1")
                .name("Product 1")
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(product);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("prod-1");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        Product product = Product.builder().id("prod-2").build();
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(product);

        verify(productRepository).delete(product);
    }

    @Test
    void testUpdateStockSuccess() {
        String id = "prod-3";
        Integer newStock = 50;
        Product updatedProduct = Product.builder()
                .id(id)
                .name("Product 3")
                .stock(newStock)
                .build();

        when(productRepository.updateStock(id, newStock)).thenReturn(updatedProduct);

        Product result = productService.updateStock(id, newStock);

        assertThat(result).isNotNull();
        assertThat(result.getStock()).isEqualTo(newStock);
        verify(productRepository).updateStock(id, newStock);
    }

    @Test
    void testGetProductByIdFound() {
        String id = "prod-4";
        Product product = Product.builder()
                .id(id)
                .name("Product 4")
                .build();

        when(productRepository.findById(id)).thenReturn(product);

        Product result = productService.getProductById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(productRepository).findById(id);
    }

    @Test
    void testGetProductByIdNotFound() {
        String id = "nonexistent";
        when(productRepository.findById(id)).thenReturn(null);

        Product result = productService.getProductById(id);

        assertThat(result).isNull();
        verify(productRepository).findById(id);
    }

    @Test
    void testFindAllReturnsList() {
        List<Product> products = Arrays.asList(
            Product.builder().id("1").name("P1").build(),
            Product.builder().id("2").name("P2").build()
        );

        when(productRepository.findALL()).thenReturn(products);

        List<Product> result = productService.findALL();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        verify(productRepository).findALL();
    }
}