package com.nequi.franchise.domain;

import java.util.List;


public interface FranchiseRepository {
    public Franchise save(Franchise franchise);
    public List<Franchise> findAll();
    public Franchise findById(String id);
    public List<Franchise> findALL();
}
