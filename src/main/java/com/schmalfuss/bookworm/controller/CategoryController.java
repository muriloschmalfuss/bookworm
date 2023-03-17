package com.schmalfuss.bookworm.controller;

import com.schmalfuss.bookworm.model.Category;
import com.schmalfuss.bookworm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> list() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category show(@PathVariable("id") Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category edit(@RequestBody Category category, @PathVariable("id") Integer id) {
        return categoryService.edit(category, id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Integer id) {
        categoryService.destroy(id);
        return "Categoria removida com sucesso";
    }


}
