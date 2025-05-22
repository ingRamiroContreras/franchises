package com.nequi.franchise.domain;

import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> save(Product product);
}
