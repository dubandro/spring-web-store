package com.geekbrains.spring.web.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private List<CartItemDto> items;
    private int totalPrice;
}
