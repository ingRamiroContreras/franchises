package com.nequi.franchise.infraestructure;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;
import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.infraestructure.entities.ProductEntity;
import com.nequi.franchise.infraestructure.entities.BranchEntity;
import com.nequi.franchise.infraestructure.jpa.ProductJpaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository productJpaRepository;


    public ProductRepositoryImpl(ProductJpaRepository ProductJpaRepository) {
        this.productJpaRepository = ProductJpaRepository;
    }

    @Override
    public Product findById(String id) {
        return toDomainEntity(
                productJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Franchise not found")));

    }

    @Override
    public List<Product> findALL() {
        return productJpaRepository.findAll()
                        .stream()
                        .map(this::toDomainEntity)
                        .toList();

    }

    @Override
    public Product save(Product product) {
        return  toDomainEntity(productJpaRepository.saveAndFlush(toJpaEntity(product)));
    }

    @Override
    public void delete(Product product) {
        productJpaRepository.deleteById(product.getId());
    }



    @Override
    @Transactional
    public Product updateStock(String productId, Integer newStock) {
        ProductEntity product = productJpaRepository.findById(productId).get();
        product.setStock(newStock);
        var productWithNewStock = productJpaRepository.saveAndFlush(product);
        return toDomainEntity(productWithNewStock);
    }

    @Override
    public List<ProductEntity> findProductsWithMaxStockByFranchise(@Param("franchiseId") String franchiseId) {
        return productJpaRepository.findProductsWithMaxStockByFranchise(franchiseId);
    }

    private ProductEntity toJpaEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .branch(toJpaEntity(product.getBranch()))
                .stock(product.getStock())
                .build();
    }

    private Product toDomainEntity(ProductEntity productEntity) {

        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .branch(toDomainEntity(productEntity.getBranch()))
                .stock(productEntity.getStock())
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