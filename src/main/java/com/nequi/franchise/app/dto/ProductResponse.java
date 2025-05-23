package com.nequi.franchise.app.dto;

import com.nequi.franchise.domain.Branch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private String id;
    private String name;
    private Branch branch;
    private Integer stock;


}
