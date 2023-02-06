package com.geekbrains.spring.web.stat.services;

import com.geekbrains.spring.web.api.dto.TopProductDto;
import com.geekbrains.spring.web.stat.integrations.CoreServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatService {
    private final CoreServiceIntegration coreServiceIntegration;

    public List<TopProductDto> topProductDtoList;
    private final Long period = 5L;
    private final Integer quantity = 5;
    // запрос каждые 10 секунд
//    @Scheduled(fixedDelayString = "PT10S")
    // запрос раз в день
    @Scheduled(fixedDelayString = "P1D")
    private void getList() {
        topProductDtoList = coreServiceIntegration.getTopItems(period, quantity);
    }

    public List<TopProductDto> getTopProducts(Long period, Integer quantity) {
        if (this.period.equals(period) && this.quantity.equals(quantity)) {
            return topProductDtoList;
        }
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
