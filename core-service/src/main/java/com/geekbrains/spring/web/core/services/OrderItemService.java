package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.dto.TopProductDto;

import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.entities.Product;
import com.geekbrains.spring.web.core.repositories.OrderItemRepository;
import com.geekbrains.spring.web.core.repositories.specifications.OrderItemsSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    // 1 вариант, урезанный
    public List<TopProductDto> getTopProductDto(Long period, Integer numProducts) {
        LocalDateTime start = LocalDateTime.now().minusDays(period);
        Map<Product, Integer> topProducts = orderItemRepository.findTopProducts(start, numProducts);
        List<TopProductDto> topProductsList = new ArrayList<>(topProducts.size());
        for (Map.Entry<Product, Integer> item : topProducts.entrySet()) {
            Product p = item.getKey();
            TopProductDto topProduct = new TopProductDto(p.getId(), p.getTitle(), p.getPrice(), p.getCategory().getTitle(), item.getValue(),BigDecimal.ZERO);
            topProductsList.add(topProduct);
        }
        return topProductsList;
    }

    // 2й вариант, с суммой по каждому продукту
    public List<TopProductDto> getTopProductFull(Long period, Integer numProducts) {
        LocalDateTime start = LocalDateTime.now().minusDays(period);
        List<OrderItem> orderItemList = orderItemRepository.findAll(OrderItemsSpecifications.createdAtAfter(start));
        // собираем из всех OrderItem сет уникальных Product
        Set<Product> productSet = orderItemList.stream().map(OrderItem::getProduct).collect(Collectors.toSet());
        // проходим по сету Product и наполняем List<TopProductDto> перебирая полученные из репо OrderItem
        List<TopProductDto> topProductsList = new ArrayList<>(productSet.size());
        for (Product p : productSet) {
            TopProductDto topProduct = new TopProductDto(p.getId(), p.getTitle(), p.getPrice(), p.getCategory().getTitle(), 0, BigDecimal.ZERO);
            for (OrderItem o : orderItemList) {
                if (Objects.equals(p.getId(), o.getProduct().getId())) {
                    topProduct.setQtyOfSales(topProduct.getQtyOfSales() + o.getQuantity());
                    topProduct.setSumOfSales(topProduct.getSumOfSales().add(o.getPrice()));
                }
            }
            topProductsList.add(topProduct);
        }
        // можно добавить вариант сортировки по сумме, или вынести в разные методы сортировку
//        topProductsList.sort((p1, p2) -> p2.getQtyOfSales() - p1.getQtyOfSales());
        topProductsList.sort(Comparator.comparingInt(TopProductDto::getQtyOfSales).reversed());
        if (topProductsList.size() > numProducts) {
            return topProductsList.subList(0, numProducts);
        } else {
            return topProductsList;
        }
    }
}
