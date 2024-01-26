package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest){
        Product product = new Product(productRequest);
        return productRepository.save(product);
    }

    public Product getById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        // Check if the product exists in the database
        if (optionalProduct.isPresent()) {
            return optionalProduct.get(); // Return the product if present
        } else {
            throw new ProductNotFoundException("Id", productId); // Throw exception if the product is not found
        }
    }
}
