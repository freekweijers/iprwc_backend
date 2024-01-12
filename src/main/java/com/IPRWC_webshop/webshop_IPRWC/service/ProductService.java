package com.IPRWC_webshop.webshop_IPRWC.service;

import com.IPRWC_webshop.webshop_IPRWC.exception.ProductNotFoundException;
import com.IPRWC_webshop.webshop_IPRWC.model.Product;
import com.IPRWC_webshop.webshop_IPRWC.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Product product) {
        // Additional logic before saving (if needed)
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct) throws ProductNotFoundException {
        // Check if the product with the given ID exists
        if (productRepository.existsById(productId)) {
            updatedProduct.setId(productId); // Ensure the ID is set for update
            return productRepository.save(updatedProduct);
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
