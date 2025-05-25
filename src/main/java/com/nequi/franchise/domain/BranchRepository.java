package com.nequi.franchise.domain;

import java.util.List;

public interface BranchRepository {
    public Branch save(Branch branch);
    public Branch findById(String id);
    public List<Branch> findALL();
}
