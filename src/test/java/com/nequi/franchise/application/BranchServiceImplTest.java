package com.nequi.franchise.application;

import com.nequi.franchise.domain.Branch;
import com.nequi.franchise.domain.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBranch_Success() {
        Branch branch = Branch.builder()
                .id("123")
                .name("Sucursal 1")
                .build();

        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch result = branchService.createBranch(branch);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("123");
        assertThat(result.getName()).isEqualTo("Sucursal 1");
        verify(branchRepository).save(any(Branch.class));
    }

    @Test
    void testGetBranchById_Found() {
        String id = "123";
        Branch branch = Branch.builder()
                .id(id)
                .name("Sucursal 2")
                .build();

        when(branchRepository.findById(id)).thenReturn(branch);

        Branch result = branchService.getBranchById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(branchRepository).findById(id);
    }

    @Test
    void testGetBranchById_NotFound() {
        String id = "nonexistent";
        when(branchRepository.findById(id)).thenReturn(null);

        Branch result = branchService.getBranchById(id);

        assertThat(result).isNull();
        verify(branchRepository).findById(id);
    }
}