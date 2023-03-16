package com.schmalfuss.bookworm.controllers;

import com.schmalfuss.bookworm.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private List<Category> categoryList = new ArrayList<>();
    private Integer counter = 1;

    @GetMapping
    public List<Category> list() {
        return categoryList;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        category.setId(counter++);
        categoryList.add(category);
        return category;
    }

    @PutMapping("/{id}")
    public Category edit(@RequestBody Category category, @PathVariable("id") Integer id) {
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

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Integer id) {
        categoryList.remove(categoryList
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null));

        return "Categoria removida com sucesso";
    }


}
