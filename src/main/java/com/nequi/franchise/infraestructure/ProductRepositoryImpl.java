package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;
import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.infraestructure.entities.ProductEntity;
import com.nequi.franchise.infraestructure.entities.BranchEntity;
import com.nequi.franchise.infraestructure.jpa.ProductJpaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository productJpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository ProductJpaRepository) {
        this.productJpaRepository = ProductJpaRepository;
    }

    @Override
    public Mono<Product> save(Product product) {
        var productEntity = productJpaRepository.saveAndFlush(toJpaEntity(product));
        return Mono.just(productEntity).map(this::toDomainEntity);
    }

    @Override
    public Mono<Void> delete(Product product) {
        productJpaRepository.deleteInBatch(List.of(toJpaEntity(product)));
        return Mono.empty();
    }

    @Override
    @Transactional
    public Mono<Product> updateStock(String productId, Integer newStock) {
        ProductEntity product = productJpaRepository.getOne(productId);
        
        product.setStock(newStock);
        var productWithNewStock = productJpaRepository.saveAndFlush(product);
        return Mono.just(toDomainEntity(productWithNewStock));
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