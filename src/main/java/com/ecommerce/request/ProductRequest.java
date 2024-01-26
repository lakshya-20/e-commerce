package com.ecommerce.request;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private long quantity;
}
