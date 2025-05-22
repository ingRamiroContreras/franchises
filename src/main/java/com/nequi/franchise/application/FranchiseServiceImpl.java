package com.nequi.franchise.application;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;

import reactor.core.publisher.Mono;

public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);

    }

}
