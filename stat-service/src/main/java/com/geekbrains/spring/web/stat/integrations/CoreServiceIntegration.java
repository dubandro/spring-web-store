package com.geekbrains.spring.web.stat.integrations;

import com.geekbrains.spring.web.api.dto.TopProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration{
    private final WebClient coreServiceWebClient;

//    https://www.baeldung.com/spring-webclient-json-list#1-mono-vs-flux

    public List<TopProductDto> getTopItems(Long period, Integer quantity) {

        return coreServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/orders/top_items")
                        .queryParam("period", period)
                        .queryParam("quantity", quantity)
                        .build())
                .accept(MediaType.APPLICATION_JSON) // посмотреть нужно ли это
                .retrieve()
                .onStatus( // подумать нужно ли это
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Популярных товаров по вашему запросу не найдено"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<TopProductDto>>() {})
                .block();
    }
}
