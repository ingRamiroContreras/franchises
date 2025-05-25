package com.nequi.franchise.application;

import java.util.List;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.infraestructure.entities.ProductEntity;

public interface ProductService {
    public Product createProduct(Product product);
    public void deleteProduct(Product product);
    public Product updateStock(String product, Integer stockValue);
    public Product getProductById(String id);
    public List<Product> findALL();
    public List<ProductEntity> findProductsWithMaxStockByFranchise(String franchiseId);
}
