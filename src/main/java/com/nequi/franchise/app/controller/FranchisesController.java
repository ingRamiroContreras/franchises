package com.nequi.franchise.app.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchise.app.dto.FranchisesRequest;
import com.nequi.franchise.app.dto.FranchisesResponse;
import com.nequi.franchise.application.BranchService;
import com.nequi.franchise.application.FranchiseService;
import com.nequi.franchise.application.ProductService;
import com.nequi.franchise.domain.Franchise;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franchises")
public class FranchisesController {

    private final BranchService branchService;
    private final ProductService productService;
    private final FranchiseService franchiseService;

    public FranchisesController(BranchService branchService, ProductService productService,
            FranchiseService franchiseService) {
        this.branchService = branchService;
        this.productService = productService;
        this.franchiseService = franchiseService;
    }

     @PostMapping
    public Mono<ResponseEntity<Franchise>> createFranchise(@RequestBody Franchise franchise) {
        return franchiseService.createFranchise(franchise)
                .map(createdFranchise -> ResponseEntity.status(HttpStatus.CREATED).body(createdFranchise));
    }

    private String generateUuId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
