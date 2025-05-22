package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;
import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.infraestructure.entities.ProductEntity;
import com.nequi.franchise.infraestructure.entities.BranchEntity;
import com.nequi.franchise.infraestructure.entities.FranchiseEntity;
import com.nequi.franchise.infraestructure.jpa.ProductJpaRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository ProductJpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository ProductJpaRepository) {
        this.ProductJpaRepository = ProductJpaRepository;
    }

    @Override
    public Mono<Product> save(Product Product) {
        var ProductEntity = ProductJpaRepository.saveAndFlush(toJpaEntity(Product));
        return Mono.just(ProductEntity).map(this::toDomainEntity);
    }

    private ProductEntity toJpaEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .branch(toJpaEntity(product.getBranch()))
                .build();
    }

    private Product toDomainEntity(ProductEntity productEntity) {

        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .branch(toDomainEntity(productEntity.getBranch()))
                .build();
    }

    private BranchEntity toJpaEntity(Branch branch) {
        return BranchEntity.builder()
                .id(branch.getId())
                .name(branch.getName())
                .build();
    }

    private Branch toDomainEntity(BranchEntity branchEntity) {

        return Branch.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .build();
    }

}