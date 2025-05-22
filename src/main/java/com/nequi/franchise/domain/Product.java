package com.nequi.franchise.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private String id;
    private String name;
    private Branch branch;
    private Integer stock;


}
