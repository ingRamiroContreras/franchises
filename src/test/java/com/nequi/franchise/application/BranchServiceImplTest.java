package com.nequi.franchise.application;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BranchServiceImplTest {

    private BranchRepository branchRepository;
    private BranchServiceImpl branchService;

    @BeforeEach
    void setUp() {
        branchRepository = mock(BranchRepository.class);
        branchService = new BranchServiceImpl(branchRepository);
    }

    @Test
    void testCreateBranch() {
        // Arrange
        Branch branch = Branch.builder().id("1").name("Test Branch").build();
        when(branchRepository.save(any(Branch.class))).thenReturn(Mono.just(branch));

        // Act
        Mono<Branch> result = branchService.createBranch(branch);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(savedBranch -> savedBranch.getId().equals("1") &&
                        savedBranch.getName().equals("Test Branch"))
                .verifyComplete();

        verify(branchRepository, times(1)).save(any(Branch.class));
    }
}