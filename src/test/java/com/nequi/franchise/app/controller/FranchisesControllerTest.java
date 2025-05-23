package com.nequi.franchise.app.controller;

import com.nequi.franchise.app.dto.*;
import com.nequi.franchise.application.*;
import com.nequi.franchise.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FranchisesControllerTest {

    private FranchiseService franchiseService;
    private BranchService branchService;
    private ProductService productService;
    private FranchisesController controller;

    @BeforeEach
    void setUp() {
        franchiseService = mock(FranchiseService.class);
        branchService = mock(BranchService.class);
        productService = mock(ProductService.class);
        controller = new FranchisesController(branchService, productService, franchiseService);
    }

    @Test
    void testCreateBranch() {
        BranchRequest request = BranchRequest.builder().name("Test Branch").build();
        Branch branch = Branch.builder().id("1").name("Test Branch").build();
        when(branchService.createBranch(any())).thenReturn(Mono.just(branch));

        Mono<ResponseEntity<BranchResponse>> result = controller.createBranch(request);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.CREATED &&
                        response.getBody() != null &&
                        response.getBody().getName().equals("Test Branch"))
                .verifyComplete();

        verify(branchService, times(1)).createBranch(any());
    }

    @Test
    void testCreateFranchise() {
        FranchisesRequest request = FranchisesRequest.builder().name("Test Franchise").build();
        Franchise franchise = Franchise.builder().id("1").name("Test Franchise").build();
        when(franchiseService.createFranchise(any())).thenReturn(Mono.just(franchise));

        Mono<ResponseEntity<FranchisesResponse>> result = controller.createFranchise(request);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.CREATED &&
                        response.getBody() != null &&
                        response.getBody().getName().equals("Test Franchise"))
                .verifyComplete();

        verify(franchiseService, times(1)).createFranchise(any());
    }

    @Test
    void testCreateProduct() {
        ProductRequest request = ProductRequest.builder().name("Test Product").build();
        Product product = Product.builder().id("1").name("Test Product").build();
        when(productService.createProduct(any())).thenReturn(Mono.just(product));

        Mono<ResponseEntity<ProductResponse>> result = controller.createProduct(request);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.CREATED &&
                        response.getBody() != null &&
                        response.getBody().getName().equals("Test Product"))
                .verifyComplete();

        verify(productService, times(1)).createProduct(any());
    }

    @Test
    void testDeleteProduct() {
        var product = Product.builder().id("1").build(); 
        when(productService.deleteProduct(product)).thenReturn(Mono.empty());

        ResponseEntity<Void> result = controller.deleteProduct(product.getId());

        StepVerifier.create(Mono.just(result))
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.OK)
                .verifyComplete();

        verify(productService, times(1)).deleteProduct(product);
    }

    @Test
    void testUpdateStock() {
        String productId = "1";
        Integer stock = 10;
        Product product = Product.builder().id(productId).stock(10).build();
        when(productService.updateStock(eq(productId), any())).thenReturn(Mono.just(product));

        Mono<ResponseEntity<ProductResponse>> result = controller.updateStock(productId, stock);

        StepVerifier.create(result)
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody() != null &&
                    response.getBody().getStock() == 10
                )
                .verifyComplete();

        verify(productService, times(1)).updateStock(eq(productId), any());
    }
}