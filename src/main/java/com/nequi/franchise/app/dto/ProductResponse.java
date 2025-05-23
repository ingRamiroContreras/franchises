package com.nequi.franchise.app.dto;

import com.nequi.franchise.domain.Branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private Branch branch;
    private Integer stock;


}
