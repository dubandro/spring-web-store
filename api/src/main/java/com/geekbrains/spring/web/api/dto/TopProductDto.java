package com.geekbrains.spring.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String categoryTitle;
    private Integer qtyOfSales;
    private Integer sumOfSales;
}
