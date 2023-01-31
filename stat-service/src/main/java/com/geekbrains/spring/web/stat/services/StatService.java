package com.geekbrains.spring.web.stat.services;

import com.geekbrains.spring.web.api.dto.TopProductDto;
import com.geekbrains.spring.web.stat.integrations.CoreServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatService {
    private final CoreServiceIntegration coreServiceIntegration;

    public List<TopProductDto> getTopProducts(Long period, Integer quantity) {
        List<TopProductDto> topProductList = coreServiceIntegration.getTopItems(period, quantity);
        // todo подумать что делаем в таких случаях
        if (topProductList.size() != quantity){
            System.out.println("topProductList.size() < quantity");
        }
        if (topProductList.isEmpty()){
            System.out.println("topProductList is Empty");
        }
        return topProductList;
    }
}
