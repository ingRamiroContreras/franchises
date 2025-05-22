package com.nequi.franchise.app.dto;

import com.nequi.franchise.domain.Franchise;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchRequest {
    private String name;
    private Franchise franchise;
}
