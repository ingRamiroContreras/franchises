package com.nequi.franchise.app.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String branchId;
    private Integer stock;


}
