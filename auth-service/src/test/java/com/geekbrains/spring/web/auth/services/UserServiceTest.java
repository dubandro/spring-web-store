package com.geekbrains.spring.web.auth.services;

import com.geekbrains.spring.web.auth.entities.User;
import com.geekbrains.spring.web.auth.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findByUsernameTest() {
        User testUserRepo = new User();
        testUserRepo.setUsername("TestName");
        testUserRepo.setEmail("TestEmail");
        testUserRepo.setPassword("TestPassword");
        Mockito.doReturn(Optional.of(testUserRepo)).when(userRepository).findByUsername("TestName");

        User testUser = userService.findByUsername("TestName").get();
        Assertions.assertNotNull(testUser);
        Assertions.assertEquals("TestName", testUser.getUsername());
        Assertions.assertEquals("TestEmail", testUser.getEmail());
        Assertions.assertEquals("TestPassword", testUser.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("TestName"));
    }
}