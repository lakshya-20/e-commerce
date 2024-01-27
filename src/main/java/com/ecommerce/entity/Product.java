package com.ecommerce.entity;

import com.ecommerce.request.CreateProductRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private long quantity;

    public Product(CreateProductRequest createProductRequest){
        this.name = createProductRequest.getName();
        this.description = createProductRequest.getDescription();
        this.price = createProductRequest.getPrice();
        this.quantity = createProductRequest.getQuantity();
    }
}
