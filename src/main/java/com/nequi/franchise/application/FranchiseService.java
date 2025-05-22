package com.nequi.franchise.application;

import com.nequi.franchise.domain.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> createFranchise(Franchise franquicia);
}
