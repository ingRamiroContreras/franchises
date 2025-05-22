package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
@Builder
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

}
