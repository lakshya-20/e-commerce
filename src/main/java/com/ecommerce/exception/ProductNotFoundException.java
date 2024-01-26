package com.ecommerce.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    private String fieldName;
    private Object fieldValue;

    public ProductNotFoundException(String fieldName, Object fieldValue){
        super("Product not found with " + fieldName + " : " + fieldValue);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
