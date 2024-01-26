package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(CreateProductRequest createProductRequest){
        Product product = new Product(createProductRequest);
        return this.productRepository.save(product);
    }

    public Product getById(Long productId){
        Optional<Product> optionalProduct = this.productRepository.findById(productId);

        // Check if the product exists in the database
        if (optionalProduct.isPresent()) {
            return optionalProduct.get(); // Return the product if present
        } else {
            throw new ProductNotFoundException("Id", productId); // Throw exception if the product is not found
        }
    }

    public Product updateProduct(UpdateProductRequest updateProductRequest){
        Product product = this.getById(updateProductRequest.getId());
        product.setName(updateProductRequest.getName());
        product.setDescription(updateProductRequest.getDescription());
        product.setPrice(updateProductRequest.getPrice());
        product.setQuantity(updateProductRequest.getQuantity());

        return this.productRepository.save(product);
    }

}
