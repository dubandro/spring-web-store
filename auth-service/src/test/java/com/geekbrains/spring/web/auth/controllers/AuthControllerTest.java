package com.geekbrains.spring.web.auth.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void createAuthTokenTest() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"bob\",\n" +
                "\t\"password\": \"100\"\n" +
                "}";

        String result = webTestClient.post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(jsonRequest), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        String token = result.replace("{\"token\":\"", "").replace("\"}", "");
//        System.out.println(token);
    }
}