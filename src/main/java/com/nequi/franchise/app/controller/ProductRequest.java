package com.nequi.franchise.app.controller;

import com.nequi.franchise.domain.Branch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;
    private Branch branch;
    private Integer stock;


}
