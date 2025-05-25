package com.nequi.franchise.application;



import java.util.List;

import com.nequi.franchise.domain.Branch;

public interface BranchService {
    public Branch createBranch(Branch branch);
    public Branch getBranchById(String id);
    public List<Branch> getAllBranches();
}
