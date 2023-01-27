package com.geekbrains.spring.web.core.repositories;

import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
    @Query(nativeQuery = true,
            value = "select product, sum(quantity) as fullQuantity from order_items group by product " +
                    "where order_items.created_at >= ?1 order by fullQuantity desc limit ?2")
    Map<Product, Integer> findTopProducts(LocalDateTime dateFrom, Integer numProducts);
}