package com.nequi.franchise.application;

import java.util.List;


import com.nequi.franchise.domain.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public void deleteProduct(Product product);
    public Product updateStock(String product, Integer stockValue);
    public Product getProductById(String id);
    public List<Product> getAllProducts();
    public List<Product> findProductsWithMaxStockByFranchise(String franchiseId);
}
