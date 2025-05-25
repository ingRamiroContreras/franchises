package com.nequi.franchise.infraestructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nequi.franchise.infraestructure.entities.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {

    @Query("SELECT p FROM ProductEntity p WHERE p.branch.franchise.id = :franchiseId AND p.stock = (SELECT MAX(p2.stock) FROM ProductEntity p2 WHERE p2.branch.id = p.branch.id)")
    List<ProductEntity> findProductsWithMaxStockByFranchise(@Param("franchiseId") String franchiseId);

}
