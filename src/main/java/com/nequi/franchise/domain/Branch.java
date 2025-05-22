package com.nequi.franchise.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {

    private String id;
    private String name;
    private Franchise franchise;

}
