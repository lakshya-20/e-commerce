package com.ecommerce.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RebateProductPriceRequest {
    @NotNull(message = "Product id must be present")
    private long id;
    private double discount;
    private double tax;
}
