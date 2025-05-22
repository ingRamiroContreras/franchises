package com.nequi.franchise.infraestructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchise.infraestructure.entities.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {

}
