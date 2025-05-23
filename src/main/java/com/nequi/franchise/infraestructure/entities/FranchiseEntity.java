package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchises")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseEntity {
    @Id
    private String id;
    @Column(name="name", unique=true)
    private String name;

}