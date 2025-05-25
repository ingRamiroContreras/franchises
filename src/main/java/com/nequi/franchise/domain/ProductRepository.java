package com.nequi.franchise.domain;

import java.util.List;



public interface ProductRepository {
    public Product save(Product product);
    public void delete(Product product);
    public Product updateStock(String productId, Integer newStock);
    public Product findById(String id);
    public List<Product> findALL();
    public List<Product> findProductsWithMaxStockByFranchise(String franchiseId);
}
