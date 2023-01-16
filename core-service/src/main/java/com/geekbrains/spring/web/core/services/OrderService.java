package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.dto.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.core.dto.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  OrderService {
    private final OrdersRepository ordersRepository;
    private final ProductsService productsService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String userName, OrderDetailsDto orderDetailsDto) {
//        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
//        Cart currentCart = cartService.getCurrentCart(cartKey);

        CartDto currentCart = cartServiceIntegration.getUserCart(userName);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUserName(userName);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setProduct(productsService.findById(o.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartServiceIntegration.clearCart(userName);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }
}
