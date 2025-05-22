package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;
import com.nequi.franchise.infraestructure.entities.FranchiseEntity;

import com.nequi.franchise.infraestructure.jpa.FranchiseJpaRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class FranchiseRepositoryImpl implements FranchiseRepository {

    private FranchiseJpaRepository franchiseJpaRepository;

    public FranchiseRepositoryImpl(FranchiseJpaRepository franchiseJpaRepository) {
        this.franchiseJpaRepository = franchiseJpaRepository;
    }

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        var franchiseEntity = franchiseJpaRepository.saveAndFlush(toJpaEntity(franchise));
        return Mono.just(franchiseEntity).map(this::toDomainEntity);
    }

    private FranchiseEntity toJpaEntity(Franchise franchise) {
        return FranchiseEntity.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }

    private Franchise toDomainEntity(FranchiseEntity franchiseEntity) {

        return Franchise.builder()
                .id(franchiseEntity.getId())
                .name(franchiseEntity.getName())
                .build();
    }

}