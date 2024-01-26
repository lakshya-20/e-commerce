package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.request.UpdateProductRequest;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return this.productService.createProduct(createProductRequest);
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable long id){
        return this.productService.getById(id);
    }

    @PutMapping("/update")
    public Product updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest){
        return this.productService.updateProduct(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        return this.productService.deleteProduct(id);
    }
}
