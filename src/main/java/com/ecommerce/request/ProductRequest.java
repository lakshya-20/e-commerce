package com.ecommerce.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, message = "Product name must have at least 3 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    @Positive(message = "Product price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Product quantity must not be negative")
    private long quantity;
}
