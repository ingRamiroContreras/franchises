package com.nequi.franchise.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;



@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);

    }
    @Override
    public Branch getBranchById(String id) {
        return branchRepository.findById(id);
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findALL();
    }
}
