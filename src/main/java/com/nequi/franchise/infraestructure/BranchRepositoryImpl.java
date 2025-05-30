package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;
import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.infraestructure.entities.BranchEntity;
import com.nequi.franchise.infraestructure.entities.FranchiseEntity;
import com.nequi.franchise.infraestructure.jpa.BranchJpaRepository;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    private BranchJpaRepository branchJpaRepository;

    public BranchRepositoryImpl(BranchJpaRepository branchJpaRepository) {
        this.branchJpaRepository = branchJpaRepository;
    }

    @Override
    public Branch save(Branch branch) {
        var branchEntity = branchJpaRepository.saveAndFlush(toJpaEntity(branch));
        return toDomainEntity(branchEntity);
    }

    @Override
    public Branch findById(String id) {
        return branchJpaRepository.findById(id)
                .map(this::toDomainEntity)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }

    @Override
    public List<Branch> findALL() {
        return branchJpaRepository.findAll()
                        .stream()
                        .map(this::toDomainEntity)
                        .toList();

    }

    private BranchEntity toJpaEntity(Branch branch) {
        return BranchEntity.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchise(toJpaEntity(branch.getFranchise()))
                .build();
    }

    private Branch toDomainEntity(BranchEntity branchEntity) {

        return Branch.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .franchise(toDomainEntity(branchEntity.getFranchise()))
                .build();
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