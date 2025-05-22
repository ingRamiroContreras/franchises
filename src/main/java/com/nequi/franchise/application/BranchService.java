package com.nequi.franchise.application;



import com.nequi.franchise.domain.Branch;

import reactor.core.publisher.Mono;

public interface BranchService {
    Mono<Branch> createBranch(Branch branch);
}
