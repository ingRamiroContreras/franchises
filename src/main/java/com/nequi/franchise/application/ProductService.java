package com.nequi.franchise.application;

import com.nequi.franchise.domain.Product;

import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(Product product);
    Mono<Product> deleteProduct(Product product);
    Mono<Void> updateStock(String product, Integer stockValue);
}
