package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.dto.CategoryDto;
import com.geekbrains.spring.web.core.converters.CategoryConverter;
import com.geekbrains.spring.web.core.entities.Category;
import com.geekbrains.spring.web.core.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public void saveNewCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }
}
