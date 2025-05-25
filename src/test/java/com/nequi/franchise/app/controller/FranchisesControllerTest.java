package com.nequi.franchise.app.controller;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nequi.franchise.application.BranchService;
import com.nequi.franchise.application.FranchiseService;
import com.nequi.franchise.application.ProductService;
import com.nequi.franchise.domain.Franchise;
import com.nequi.franchise.app.dto.*;


class FranchisesControllerTest {

    @Mock
    private BranchService branchService;

    @Mock
    private ProductService productService;

    @Mock
    private FranchiseService franchiseService;

    @InjectMocks
    private FranchisesController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethodName_Found() {
        String id = UUID.randomUUID().toString();
        Franchise franchise = Franchise.builder()
                .id(id)
                .name("Test Franchise")
                .build();

        when(franchiseService.getFranchiseById(id)).thenReturn(franchise);

        ResponseEntity<FranchisesResponse> response = controller.getMethodName(id);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Test Franchise");
        verify(franchiseService).getFranchiseById(id);
    }

    @Test
    void testGetMethodName_NotFound() {
        String id = UUID.randomUUID().toString();
        when(franchiseService.getFranchiseById(id)).thenReturn(null);
        ResponseEntity<FranchisesResponse> response = controller.getMethodName(id);
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        assertThat(response.getBody()).isNull();
        verify(franchiseService).getFranchiseById(id);
    }

    @Test
    void testCreateFranchise_Success() {
        FranchisesRequest request = new FranchisesRequest();
        request.setName("New Franchise");
        String generatedId = UUID.randomUUID().toString();

        when(franchiseService.createFranchise(any(Franchise.class)))
            .thenAnswer(invocation -> {
                Franchise f = invocation.getArgument(0);
                f.setId(generatedId);
                return f;
            });

        ResponseEntity<FranchisesResponse> response = controller.createFranchise(request);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getName()).isEqualTo("New Franchise");
        assertThat(response.getBody().getId()).isEqualTo(generatedId);
        verify(franchiseService).createFranchise(any(Franchise.class));
    }

    // Puedes agregar más tests para cada método según el comportamiento esperado
}
