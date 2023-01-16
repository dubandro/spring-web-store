package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.core.converters.OrderConverter;
import com.geekbrains.spring.web.core.dto.OrderDetailsDto;
import com.geekbrains.spring.web.core.dto.OrderDto;
import com.geekbrains.spring.web.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String userName, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(userName, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String userName) {
        return orderService.findOrdersByUsername(userName).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
