package com.nequi.franchise.application;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FranchiseServiceImplTest {

    private FranchiseRepository franchiseRepository;
    private FranchiseServiceImpl franchiseService;

    @BeforeEach
    void setUp() {
        franchiseRepository = mock(FranchiseRepository.class);
        franchiseService = new FranchiseServiceImpl(franchiseRepository);
    }

    @Test
    void testCreateFranchise() {
        // Arrange
        Franchise franchise = Franchise.builder().id("1").name("Test Franchise").build();
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(franchise));

        // Act
        Mono<Franchise> result = franchiseService.createFranchise(franchise);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(savedFranchise -> savedFranchise.getId().equals("1") &&
                        savedFranchise.getName().equals("Test Franchise"))
                .verifyComplete();

        verify(franchiseRepository, times(1)).save(any(Franchise.class));
    }
}