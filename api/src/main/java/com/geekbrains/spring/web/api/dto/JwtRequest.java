package com.geekbrains.spring.web.api.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
