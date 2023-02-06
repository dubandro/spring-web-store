package com.geekbrains.spring.web.auth.services;

import com.geekbrains.spring.web.auth.entities.Role;
import com.geekbrains.spring.web.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
