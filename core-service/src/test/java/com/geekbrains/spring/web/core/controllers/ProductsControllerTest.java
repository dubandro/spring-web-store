package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.dto.ProductDto;
import com.geekbrains.spring.web.core.entities.Category;
import com.geekbrains.spring.web.core.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class ProductsControllerTest extends SpringBootTestBase {

    @Autowired
    CategoryService categoryService;
    @Autowired
    WebTestClient webTestClient;

    @Test
    void CreateGetDeleteProduct() {
        ProductDto testProduct = new ProductDto();
        testProduct.setTitle("TestProduct");
        testProduct.setCategoryTitle("TestCategory");
        testProduct.setPrice(100);

        Category category = new Category();
        category.setTitle("TestCategory");
        categoryService.saveNewCategory(category);

        ProductDto newProduct = webTestClient.post()
                .uri("/api/v1/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(testProduct), ProductDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        ProductDto productDto = webTestClient.get()
                .uri("/api/v1/products/" + newProduct.getId())
                .exchange()
                .expectStatus().isOk().expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        assertEquals(productDto.getTitle(), newProduct.getTitle());
        assertEquals(productDto.getPrice(), newProduct.getPrice());
        assertEquals(productDto.getCategoryTitle(), newProduct.getCategoryTitle());
        assertEquals(productDto.getTitle(), testProduct.getTitle());
        assertEquals(productDto.getPrice(), testProduct.getPrice());
        assertEquals(productDto.getCategoryTitle(), testProduct.getCategoryTitle());

        webTestClient.delete()
                .uri("/api/v1/products/" + newProduct.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/api/v1/products/" + newProduct.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}