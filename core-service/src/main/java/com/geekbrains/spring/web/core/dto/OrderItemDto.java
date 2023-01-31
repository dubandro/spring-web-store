package com.geekbrains.spring.web.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private String productTitle;
    private Integer quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
}
