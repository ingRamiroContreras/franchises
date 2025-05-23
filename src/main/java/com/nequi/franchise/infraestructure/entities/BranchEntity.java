package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branches")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchEntity {
    @Id
    private String id;

    @Column(name="name", unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private FranchiseEntity franchise;

   
}