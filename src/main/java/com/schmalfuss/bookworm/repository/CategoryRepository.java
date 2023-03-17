package com.schmalfuss.bookworm.repository;

import com.schmalfuss.bookworm.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private List<Category> categoryList = new ArrayList<>();
    private Integer counter = 1;

    public List<Category> getAll() {
        return  categoryList;
    }

    public Category getById(Integer id) {
        return categoryList
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Category create(Category category) {
        category.setId(counter++);
        categoryList.add(category);
        return category;
    }

    public Category edit(Category category, Integer id) {
        category.setId(id);

        int index = categoryList.indexOf(categoryList
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null)
        );

        categoryList.set(index, category);

        return category;
    }

    public void destroy(Integer id) {
        categoryList.remove(categoryList
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null));
    }
}
