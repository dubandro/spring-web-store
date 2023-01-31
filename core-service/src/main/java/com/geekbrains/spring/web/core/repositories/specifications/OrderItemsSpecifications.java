package com.geekbrains.spring.web.core.repositories.specifications;

import com.geekbrains.spring.web.core.entities.OrderItem;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderItemsSpecifications {
    public static Specification<OrderItem> createdAtAfter(LocalDateTime createdAt) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAt);
    }
}
