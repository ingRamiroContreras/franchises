package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;
import com.nequi.franchise.domain.Product;
import com.nequi.franchise.infraestructure.entities.FranchiseEntity;

import com.nequi.franchise.infraestructure.jpa.FranchiseJpaRepository;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class FranchiseRepositoryImpl implements FranchiseRepository {

    private FranchiseJpaRepository franchiseJpaRepository;

    public FranchiseRepositoryImpl(FranchiseJpaRepository franchiseJpaRepository) {
        this.franchiseJpaRepository = franchiseJpaRepository;
    }

    @Override
    public Franchise save(Franchise franchise) {
        return toDomainEntity(franchiseJpaRepository.saveAndFlush(toJpaEntity(franchise)));

    }

    @Override
    public Franchise findById(String id) {
        return franchiseJpaRepository.findById(id)
                .map(this::toDomainEntity)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

    }

    @Override
    public List<Franchise> findALL() {
        return franchiseJpaRepository.findAll()
                        .stream()
                        .map(this::toDomainEntity)
                        .toList();

    }

    @Override
    public List<Franchise> findAll() {
        return franchiseJpaRepository.findAll()
                .stream()
                .map(this::toDomainEntity)
                .toList();
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