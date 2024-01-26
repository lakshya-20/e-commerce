package com.ecommerce.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateProductRequest {
    @NotNull(message = "Product id must be present")
    private long id;

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
