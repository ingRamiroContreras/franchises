package com.nequi.franchise.application;

import org.springframework.stereotype.Service;

import com.nequi.franchise.domain.Product;
import com.nequi.franchise.domain.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Mono<Void> deleteProduct(Product product) {
        return this.productRepository.delete(product);
    }

    @Override
    public Mono<Product> updateStock(String product, Integer stockValue) {
        return this.productRepository.updateStock(product, stockValue);
    }
    @Override
    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

}
