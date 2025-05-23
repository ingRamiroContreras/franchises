package com.nequi.franchise.application;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = Product.builder().id("1").name("Test Product").build();
        when(productRepository.save(any(Product.class))).thenReturn(Mono.just(product));

        // Act
        Mono<Product> result = productService.createProduct(product);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(savedProduct -> savedProduct.getId().equals("1") &&
                        savedProduct.getName().equals("Test Product"))
                .verifyComplete();

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        var product = Product
                .builder()
                .id("1")
                .name("Test Product")
                .build();
        when(productRepository.delete(product)).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = productService.deleteProduct(product);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(productRepository, times(1)).delete(product);
    }

    /** 
    @Test
    void testUpdateStock() {
        // Arrange
        String productId = "1";
        Product existingProduct = Product.builder().id(productId).stock(5).build();
        Product updatedProduct = Product.builder().id(productId).stock(10).build();
        when(productRepository.updateStock(any(Product.class))).thenReturn(Mono.just(updatedProduct));

        // Act
        Mono<Product> result = productService.updateStock(productId, 10);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(product -> product.getId().equals(productId) &&
                        product.getStock() == 10)
                .verifyComplete();

        verify(productRepository, times(1)).save(any(Product.class));
    }
        */
}