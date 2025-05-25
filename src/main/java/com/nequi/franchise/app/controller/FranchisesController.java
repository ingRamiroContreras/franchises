package com.nequi.franchise.app.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchise.app.dto.BranchRequest;
import com.nequi.franchise.app.dto.BranchResponse;
import com.nequi.franchise.app.dto.FranchisesRequest;
import com.nequi.franchise.app.dto.FranchisesResponse;
import com.nequi.franchise.app.dto.ProductRequest;
import com.nequi.franchise.app.dto.ProductResponse;
import com.nequi.franchise.application.BranchService;
import com.nequi.franchise.application.FranchiseService;
import com.nequi.franchise.application.ProductService;
import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.Product;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class FranchisesController {

        private final BranchService branchService;
        private final ProductService productService;
        private final FranchiseService franchiseService;

        public FranchisesController(BranchService branchService, ProductService productService,
                        FranchiseService franchiseService) {
                this.branchService = branchService;
                this.productService = productService;
                this.franchiseService = franchiseService;
        }

        @GetMapping("/franchise/{id}")
        public Mono<ResponseEntity<FranchisesResponse>> getMethodName(@RequestParam String id) {
                return franchiseService.getFranchiseById(id)
                                .map(franchise -> ResponseEntity.ok(FranchisesResponse.builder()
                                                .name(franchise.getName())
                                                .id(franchise.getId())
                                                .build()))
                                .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @GetMapping("/franchise/branch/{id}")
        public Mono<ResponseEntity<BranchResponse>> getBranchById(@RequestParam String id) {
                return branchService.getBranchById(id)
                                .map(branch -> ResponseEntity.ok(BranchResponse.builder()
                                                .name(branch.getName())
                                                .id(branch.getId())
                                                .franchise(branch.getFranchise())
                                                .build()))
                                .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @GetMapping("/franchise/branch/product/{id}")
        public Mono<ResponseEntity<ProductResponse>> getProductById(@RequestParam String id) {
                return productService.getProductById(id)
                                .map(product -> ResponseEntity.ok(ProductResponse.builder()
                                                .name(product.getName())
                                                .id(product.getId())
                                                .branch(product.getBranch())
                                                .stock(product.getStock())
                                                .build()))
                                .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @PostMapping("/franchise")
        public Mono<ResponseEntity<FranchisesResponse>> createFranchise(
                        @RequestBody FranchisesRequest franchiseRequest) {
                var franchise = Franchise.builder()
                                .name(franchiseRequest.getName())
                                .id(generateUuId())
                                .build();

                return franchiseService.createFranchise(franchise)
                                .map(createdFranchise -> ResponseEntity.status(HttpStatus.CREATED)
                                                .body(FranchisesResponse.builder()
                                                                .name(createdFranchise.getName())
                                                                .id(createdFranchise.getId())
                                                                .build()));
        }

        @PostMapping("/franchise/branch")
        public Mono<ResponseEntity<BranchResponse>> createBranch(@RequestBody BranchRequest branchRequest) {
                var branch = Branch.builder()
                                .name(branchRequest.getName())
                                .id(generateUuId())
                                .franchise(Franchise.builder()
                                                .id(branchRequest.getFranchiseId())
                                                .build())
                                .build();

                return branchService.createBranch(branch)
                                .map(createdBranch -> ResponseEntity.status(HttpStatus.CREATED)
                                                .body(BranchResponse.builder()
                                                                .name(createdBranch.getName())
                                                                .id(createdBranch.getId())
                                                                .build()));
        }

        @PostMapping("/franchise/branch/product")
        public Mono<ResponseEntity<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
                var product = Product.builder()
                                .name(productRequest.getName())
                                .branch(Branch.builder()
                                                .id(productRequest.getBranchId())
                                                .build())
                                .stock(productRequest.getStock())
                                .id(generateUuId())
                                .build();

                return productService.createProduct(product)
                                .map(createdProduct -> ResponseEntity.status(HttpStatus.CREATED)
                                                .body(ProductResponse.builder()
                                                                .name(createdProduct.getName())
                                                                .id(createdProduct.getId())
                                                                .branch(createdProduct.getBranch())
                                                                .stock(createdProduct.getStock())
                                                                .build()));
        }

        @PutMapping("/franchise/branch/product/{idProduct}/stock/{stock}")
        public Mono<ResponseEntity<ProductResponse>> updateStock(@PathVariable String idProduct,
                        @PathVariable Integer stock) {
                return productService.updateStock(idProduct, stock)
                                .map(createdProduct -> ResponseEntity.status(HttpStatus.OK)
                                                .body(ProductResponse.builder()
                                                                .name(createdProduct.getName())
                                                                .id(createdProduct.getId())
                                                                .branch(createdProduct.getBranch())
                                                                .stock(createdProduct.getStock())
                                                                .build()));

        }

        @DeleteMapping("/franchise/branch/product/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
                productService.deleteProduct(Product.builder().id(productId).build());
                return ResponseEntity.ok().build();
        }

        private String generateUuId() {

                return UUID.randomUUID().toString();
        }

}
