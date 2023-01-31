package com.geekbrains.spring.web.auth.controllers;

import com.geekbrains.spring.web.api.dto.JwtRequest;
import com.geekbrains.spring.web.api.dto.JwtResponse;
import com.geekbrains.spring.web.api.dto.RegUserDto;
import com.geekbrains.spring.web.api.exceptions.AppError;
import com.geekbrains.spring.web.auth.entities.User;
import com.geekbrains.spring.web.auth.services.UserService;
import com.geekbrains.spring.web.auth.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }

    @PostMapping("/registration")
    public ResponseEntity<?> createAuthToken(@RequestBody RegUserDto regUserDto) {
        if (!regUserDto.getPassword().equals(regUserDto.getConfirm())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Check pass or confirm"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(regUserDto.getUserName()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "This USERNAME  is busy"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(regUserDto.getUserName());
        user.setPassword(passwordEncoder.encode(regUserDto.getPassword()));
        user.setEmail(regUserDto.getEmail());
        userService.createNewUser(user);

        UserDetails userDetails = userService.loadUserByUsername(regUserDto.getUserName());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
