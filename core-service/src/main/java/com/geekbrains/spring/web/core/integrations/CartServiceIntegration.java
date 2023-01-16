package com.geekbrains.spring.web.core.integrations;

import com.geekbrains.spring.web.api.dto.CartDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;
    public CartDto getUserCart(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
//                .onStatus(
//                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
//                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена"))
//                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clearCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

//    public void clearUserCart(String username) {
//        cartServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }
//
//    public CartDto getUserCart(String username) {
//        CartDto cart = cartServiceWebClient.get()
//                .uri("/api/v1/cart/0")
//                .header("username", username)
//                .retrieve()
//                .bodyToMono(CartDto.class)
//                .block();
//        return cart;
//    }
}
