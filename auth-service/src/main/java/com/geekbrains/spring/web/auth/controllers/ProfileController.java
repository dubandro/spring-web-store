package com.geekbrains.spring.web.auth.controllers;

import com.geekbrains.spring.web.api.dto.JwtRequest;
import com.geekbrains.spring.web.api.dto.ProfileDto;
import com.geekbrains.spring.web.auth.entities.User;
import com.geekbrains.spring.web.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public ProfileDto getCurrentUserInfo(@RequestBody JwtRequest authRequest) {
        String username = authRequest.getUsername();
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new ProfileDto(user.getUsername()); //todo переделать ProfileDto чтоб отдавал больше инфы, сделать конвертер из User
    }
}
