package com.nequi.franchise.domain;

import reactor.core.publisher.Mono;

public interface FranquiciaRepository {
    Mono<Franchise> save(Franchise franchise);
}
