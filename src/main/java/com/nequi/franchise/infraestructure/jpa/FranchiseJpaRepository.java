package com.nequi.franchise.infraestructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchise.infraestructure.entities.FranchiseEntity;

public interface FranchiseJpaRepository extends JpaRepository<FranchiseEntity, String>  {

}
