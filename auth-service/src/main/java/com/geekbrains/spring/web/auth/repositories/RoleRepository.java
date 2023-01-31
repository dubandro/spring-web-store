package com.geekbrains.spring.web.auth.repositories;

import com.geekbrains.spring.web.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
