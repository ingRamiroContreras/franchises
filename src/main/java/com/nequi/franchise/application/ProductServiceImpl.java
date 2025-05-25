package com.nequi.franchise.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    @Override
    public Product updateStock(String product, Integer stockValue) {
        return this.productRepository.updateStock(product, stockValue);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findALL();

    }

    @Override
    public List<Product> findProductsWithMaxStockByFranchise(String franchiseId) {
        return findProductsWithMaxStockByFranchise(franchiseId);
    }


}
