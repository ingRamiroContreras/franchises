package com.nequi.franchise.app.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductMaxStockResponse {

    private String productId;
    private String productName;
    private Integer stock;
    private String branchId;
    private String branchName;

}
