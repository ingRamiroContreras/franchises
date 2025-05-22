package com.nequi.franchise.infraestructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchise.infraestructure.entities.BranchEntity;

public interface BranchJpaRepository extends JpaRepository<BranchEntity, String> {

}
