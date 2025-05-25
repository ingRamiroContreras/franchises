package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;
import com.nequi.franchise.infraestructure.entities.FranchiseEntity;

import com.nequi.franchise.infraestructure.jpa.FranchiseJpaRepository;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
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

    @Override
    public Mono<Franchise> findById(String id) {
        return Mono.fromCallable(() -> franchiseJpaRepository.findById(id))
                .flatMap(optionalEntity -> optionalEntity
                        .map(entity -> Mono.just(toDomainEntity(entity)))
                        .orElseGet(Mono::empty));
    }

    @Override
    public Flux<Franchise> findAll() {
        return Flux.fromIterable(franchiseJpaRepository.findAll())
                .map(this::toDomainEntity);
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