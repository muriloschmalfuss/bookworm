package com.schmalfuss.bookworm.controller;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
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
    public List<CategoryDTO> list() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO show(@PathVariable("id") Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO edit(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id) {
        return categoryService.edit(categoryDTO, id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Integer id) {
        categoryService.destroy(id);
        return "Categoria removida com sucesso";
    }


}
