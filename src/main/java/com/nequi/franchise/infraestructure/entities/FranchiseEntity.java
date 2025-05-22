package com.nequi.franchise.infraestructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "franchises")
@Data
@Builder
public class FranchiseEntity {
    @Id
    private String id;
    private String name;

}