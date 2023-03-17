package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.Category;
import com.schmalfuss.bookworm.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository = new CategoryRepository();

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Category getById(Integer id) {
        return categoryRepository.getById(id);
    }

    public Category create(Category category) {
        return categoryRepository.create(category);
    }

    public Category edit(Category category, Integer id) {
        return categoryRepository.edit(category, id);
    }

    public void destroy(Integer id) {
        categoryRepository.destroy(id);
    }
}
