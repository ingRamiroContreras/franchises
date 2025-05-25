package com.nequi.franchise.application;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.domain.FranchiseRepository;

class FranchiseServiceImplTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseServiceImpl franchiseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFranchise_Success() {
        Franchise franchise = Franchise.builder()
                .id("abc-123")
                .name("Franchise A")
                .build();

        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        Franchise result = franchiseService.createFranchise(franchise);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("abc-123");
        assertThat(result.getName()).isEqualTo("Franchise A");
        verify(franchiseRepository).save(any(Franchise.class));
    }

    @Test
    void testGetFranchiseById_Found() {
        String id = "fran-001";
        Franchise franchise = Franchise.builder()
                .id(id)
                .name("Franchise B")
                .build();

        when(franchiseRepository.findById(id)).thenReturn(franchise);

        Franchise result = franchiseService.getFranchiseById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(franchiseRepository).findById(id);
    }

    @Test
    void testGetFranchiseById_NotFound() {
        String id = "nonexistent";
        when(franchiseRepository.findById(id)).thenReturn(null);

        Franchise result = franchiseService.getFranchiseById(id);

        assertThat(result).isNull();
        verify(franchiseRepository).findById(id);
    }

    @Test
    void testGetAllFranchises_ReturnsList() {
        List<Franchise> franchises = Arrays.asList(
            Franchise.builder().id("1").name("F1").build(),
            Franchise.builder().id("2").name("F2").build()
        );

        when(franchiseRepository.findAll()).thenReturn(franchises);

        List<Franchise> result = franchiseService.getAllFranchises();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        verify(franchiseRepository).findAll();
    }
}