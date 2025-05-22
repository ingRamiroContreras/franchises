package com.nequi.franchise.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FranchisesResponse {
    String id;
    String name;
    
}
