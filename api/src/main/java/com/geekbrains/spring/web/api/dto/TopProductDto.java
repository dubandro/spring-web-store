package com.geekbrains.spring.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopProductDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;
    private Integer qtyOfSales;
    private BigDecimal sumOfSales;
}
