package com.nequi.franchise.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Franchise {
    String id;
    String name;
}
