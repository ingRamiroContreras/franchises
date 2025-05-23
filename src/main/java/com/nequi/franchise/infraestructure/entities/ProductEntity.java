package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    @Column(name="name", unique=true)
    private String name;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

}
