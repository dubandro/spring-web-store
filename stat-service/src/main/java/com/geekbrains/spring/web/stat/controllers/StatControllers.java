package com.geekbrains.spring.web.stat.controllers;

import com.geekbrains.spring.web.api.dto.TopProductDto;
import com.geekbrains.spring.web.stat.services.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class StatControllers {
    private final StatService statService;

    @GetMapping("/top_items")
    public List<TopProductDto> getTopProducts(
            @RequestParam(name = "period", defaultValue = "10") Long period,
            @RequestParam(name = "quantity", defaultValue = "5") Integer quantity
    ){
        return statService.getTopProducts(period, quantity);
    }
}
