package com.nequi.franchise.application;

import org.springframework.stereotype.Service;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;

import reactor.core.publisher.Mono;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Mono<Branch> createBranch(Branch branch) {
        return branchRepository.save(branch);

    }
    @Override
    public Mono<Branch> getBranchById(String id) {
        return branchRepository.findById(id);
    }
}
