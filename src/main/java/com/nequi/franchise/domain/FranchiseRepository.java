package com.nequi.franchise.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> save(Franchise franchise);
    Flux<Franchise> findAll();
    Mono<Franchise> findById(String id);
}
