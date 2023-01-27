package com.geekbrains.spring.web.carts.integrations;

import com.geekbrains.spring.web.api.dto.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

//    private final RestTemplate restTemplate;
//    public Optional<ProductDto> findProductById(Long id) {
//        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/app/api/v1/products/" + id, ProductDto.class));
//    }

    private final WebClient productServiceWebClient;
    public ProductDto findProductById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар в продуктовом сервисе не найден"))
                )
                .bodyToMono(ProductDto.class)
                .block();
    }
}
