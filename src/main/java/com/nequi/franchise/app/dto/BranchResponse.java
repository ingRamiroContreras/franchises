package com.nequi.franchise.app.dto;

import com.nequi.franchise.domain.Franchise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    private String id;
    private String name;
    private Franchise franchise;

}
