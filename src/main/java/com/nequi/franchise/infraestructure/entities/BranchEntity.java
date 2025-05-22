package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "branches")
@Data
@Builder
public class BranchEntity {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private FranchiseEntity franchise;

   
}