package com.nequi.franchise.domain;

import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Void> delete(Product product);
    Mono<Product> updateStock(String productId, Integer newStock);
}
