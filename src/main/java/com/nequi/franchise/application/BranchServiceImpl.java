package com.nequi.franchise.application;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;

import reactor.core.publisher.Mono;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Mono<Branch> createBranch(Branch branch) {
        return branchRepository.save(branch);

    }

}
