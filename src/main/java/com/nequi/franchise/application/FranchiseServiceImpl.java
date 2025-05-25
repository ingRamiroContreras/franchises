package com.nequi.franchise.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);

    }

    @Override
    public Franchise getFranchiseById(String id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

}
