package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
import com.schmalfuss.bookworm.model.entity.CategoryEntity;
import com.schmalfuss.bookworm.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        List<CategoryEntity> entityList = categoryRepository.findAll();
        return entityList.stream().map(categoryEntity -> new CategoryDTO().update(categoryEntity)).toList();
    }

    public CategoryDTO getById(Integer id) {
        Optional<CategoryEntity> categoryEntityOp = categoryRepository.findById(id);
        if (categoryEntityOp.isEmpty()) {
            CategoryEntity categoryEntity = categoryEntityOp.get();
            return new CategoryDTO().update(categoryEntity);
        }

        throw new EntityNotFoundException();
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity category = new CategoryEntity().update(categoryDTO);

        categoryRepository.save(category);
        return new CategoryDTO().update(category);
    }

    public CategoryDTO edit(CategoryDTO categoryDTO, Integer id) {
        Optional<CategoryEntity> categoryEntityOp = categoryRepository.findById(id);

        if(categoryRepository.existsById(id)) {
            CategoryEntity categoryEntity = new CategoryEntity().update(categoryDTO);
            categoryEntity.setId(id);
            categoryEntity = categoryRepository.save(categoryEntity);

            return new CategoryDTO().update(categoryEntity);
        }

        throw new EntityNotFoundException();
    }

    public void destroy(Integer id) {
        Optional<CategoryEntity> categoryEntityOp = categoryRepository.findById(id);
        if (categoryEntityOp.isPresent()) {
            CategoryEntity categoryEntity = categoryEntityOp.get();
            categoryRepository.delete(categoryEntity);
            return;
        }

        throw new EntityNotFoundException();
    }
}
