package com.geekbrains.spring.web.auth.repositories;

import com.geekbrains.spring.web.auth.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    @Test
    public void findByNameTest() {
        User testUserRepo = new User();
        testUserRepo.setUsername("TestName");
        testUserRepo.setEmail("TestEmail");
        testUserRepo.setPassword("TestPassword");
        this.entityManager.persist(testUserRepo);
        User user = this.repository.findByUsername("TestName").orElseThrow();
        assertEquals("TestName", user.getUsername());
        assertEquals("TestEmail", user.getEmail());
    }
}