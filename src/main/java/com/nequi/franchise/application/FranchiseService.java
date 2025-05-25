package com.nequi.franchise.application;

import java.util.List;

import com.nequi.franchise.domain.Franchise;


public interface FranchiseService {
    public Franchise createFranchise(Franchise franchise);
    public Franchise getFranchiseById(String id);
    public List<Franchise> getAllFranchises();
}
