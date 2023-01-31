package com.geekbrains.spring.web.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegUserDto {
    private String userName;
    private String password;
    private String confirm;
    private String email;
}
