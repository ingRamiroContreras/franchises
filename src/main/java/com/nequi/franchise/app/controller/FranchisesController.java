package com.nequi.franchise.app.controller;

import java.util.List;
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
import com.nequi.franchise.app.dto.ProductMaxStockResponse;
import com.nequi.franchise.app.dto.ProductRequest;
import com.nequi.franchise.app.dto.ProductResponse;
import com.nequi.franchise.application.BranchService;
import com.nequi.franchise.application.FranchiseService;
import com.nequi.franchise.application.ProductService;
import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.Product;

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
        public ResponseEntity<FranchisesResponse> getMethodName(@RequestParam String id) {
                Franchise franchise = franchiseService.getFranchiseById(id);
                if (franchise != null) {
                        return ResponseEntity.ok(FranchisesResponse.builder()
                                        .name(franchise.getName())
                                        .id(franchise.getId())
                                        .build());
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/franchise/branch/{id}")
        public ResponseEntity<BranchResponse> getBranchById(@RequestParam String id) {
                Branch branch = branchService.getBranchById(id);
                if (branch != null) {
                        return ResponseEntity.ok(BranchResponse.builder()
                                        .name(branch.getName())
                                        .id(branch.getId())
                                        .franchise(branch.getFranchise())
                                        .build());
                } else {
                        return ResponseEntity.notFound().build();
                }

        }

        @GetMapping("/franchise/branch/product/{id}")
        public ResponseEntity<ProductResponse> getProductById(@RequestParam String id) {
                Product product = productService.getProductById(id);
                if (product != null) {
                        return ResponseEntity.ok(ProductResponse.builder()
                                        .name(product.getName())
                                        .id(product.getId())
                                        .branch(product.getBranch())
                                        .stock(product.getStock())
                                        .build());
                } else {
                        return ResponseEntity.notFound().build();
                }

        }

        @PostMapping("/franchise")
        public ResponseEntity<FranchisesResponse> createFranchise(
                        @RequestBody FranchisesRequest franchiseRequest) {
                Franchise franchise = Franchise.builder()
                                .name(franchiseRequest.getName())
                                .id(generateUuId())
                                .build();
                Franchise franchiseCreated = franchiseService.createFranchise(franchise);
                if (franchiseCreated != null) {
                        return ResponseEntity.status(HttpStatus.CREATED)
                                        .body(FranchisesResponse.builder()
                                                        .name(franchiseCreated.getName())
                                                        .id(franchiseCreated.getId())
                                                        .build());
                } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }

        @PostMapping("/franchise/branch")
        public ResponseEntity<BranchResponse> createBranch(@RequestBody BranchRequest branchRequest) {
                Branch branch = Branch.builder()
                                .name(branchRequest.getName())
                                .id(generateUuId())
                                .franchise(Franchise.builder()
                                                .id(branchRequest.getFranchiseId())
                                                .build())
                                .build();
                Branch branchResponse = branchService.createBranch(branch);

                if (branchResponse != null) {
                        return ResponseEntity.status(HttpStatus.CREATED)
                                        .body(BranchResponse.builder()
                                                        .name(branchResponse.getName())
                                                        .id(branchResponse.getId())
                                                        .franchise(branchResponse.getFranchise())
                                                        .build());
                } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }

        @PostMapping("/franchise/branch/product")
        public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
                Product product = Product.builder()
                                .name(productRequest.getName())
                                .branch(Branch.builder()
                                                .id(productRequest.getBranchId())
                                                .build())
                                .stock(productRequest.getStock())
                                .id(generateUuId())
                                .build();

                Product productResponse = productService.createProduct(product);
                if (productResponse != null) {
                        return ResponseEntity.status(HttpStatus.CREATED)
                                        .body(ProductResponse.builder()
                                                        .name(productResponse.getName())
                                                        .id(productResponse.getId())
                                                        .branch(productResponse.getBranch())
                                                        .stock(productResponse.getStock())
                                                        .build());
                } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }

        @PutMapping("/franchise/branch/product/{idProduct}/stock/{stock}")
        public ResponseEntity<ProductResponse> updateStock(@PathVariable String idProduct,
                        @PathVariable Integer stock) {

                Product product = productService.getProductById(idProduct);
                if (product == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                if (stock < 0) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                if (product.getStock() == stock) {
                        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
                }

                Product updatedProduct = productService.updateStock(idProduct, stock);
                return ResponseEntity.ok(ProductResponse.builder()
                                .name(updatedProduct.getName())
                                .id(updatedProduct.getId())
                                .branch(updatedProduct.getBranch())
                                .stock(updatedProduct.getStock())
                                .build());
        }

        @DeleteMapping("/franchise/branch/product/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
                productService.deleteProduct(Product.builder().id(productId).build());
                return ResponseEntity.ok().build();
        }

        private String generateUuId() {

                return UUID.randomUUID().toString();
        }

        @GetMapping("/max-stock-by-franchise/{franchiseId}")
        public List<ProductMaxStockResponse> getProductsWithMaxStockByFranchise(@PathVariable String franchiseId) {
                List<Product> products = productService.findProductsWithMaxStockByFranchise(franchiseId);

                if (products == null || products.isEmpty()) {
                        return List.of();
                }

                return products.stream()
                                .map(prod -> ProductMaxStockResponse.builder()
                                                .productId(prod.getId())
                                                .productName(prod.getName())
                                                .stock(prod.getStock())
                                                .branchId(prod.getBranch().getId())
                                                .branchName(prod.getBranch().getName())
                                                .build())
                                .toList();
        }

        @GetMapping("/franchise")
        public List<FranchisesResponse> getFranchises() {
                List<Franchise> franchise = franchiseService.getAllFranchises();
                if (franchise != null) {
                        return franchise.stream()
                                        .map(franch -> FranchisesResponse.builder()
                                                        .name(franch.getName())
                                                        .id(franch.getId())
                                                        .build())
                                        .toList();
                } else {
                        return List.of();
                }
        }

        @GetMapping("/franchise/branch")
        public List<BranchResponse> getBranches() {
                List<Branch> franchise = branchService.getAllBranches();
                if (franchise != null) {
                        return franchise.stream()
                                        .map(franch -> BranchResponse.builder()
                                                        .name(franch.getName())
                                                        .id(franch.getId())
                                                        .build())
                                        .toList();
                } else {
                        return List.of();
                }
        }

        @GetMapping("/franchise/branch/product")
        public List<ProductResponse> getProducts() {
                List<Product> franchise = productService.getAllProducts();
                if (franchise != null) {
                        return franchise.stream()
                                        .map(franch -> ProductResponse.builder()
                                                        .name(franch.getName())
                                                        .id(franch.getId())
                                                        .build())
                                        .toList();
                } else {
                        return List.of();
                }
        }

}
