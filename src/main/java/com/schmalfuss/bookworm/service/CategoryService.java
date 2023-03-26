package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
import com.schmalfuss.bookworm.model.entity.CategoryEntity;
import com.schmalfuss.bookworm.model.mapper.CategoryMapper;
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

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> getAll() {
        List<CategoryEntity> entityList = categoryRepository.findAll();
        return entityList.stream().map(categoryEntity -> categoryMapper.update(categoryEntity)).toList();
    }

    public CategoryDTO getById(Long id) {
        Optional<CategoryEntity> categoryEntityOp = categoryRepository.findById(id);
        if (categoryEntityOp.isPresent()) {
            CategoryEntity categoryEntity = categoryEntityOp.get();
            return categoryMapper.update(categoryEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryMapper.update(categoryDTO);

        categoryRepository.save(category);
        return categoryMapper.update(category);
    }

    public CategoryDTO edit(CategoryDTO categoryDTO, Long id) {
        if(categoryRepository.existsById(id)) {
            CategoryEntity categoryEntity = categoryMapper.update(categoryDTO);
            categoryEntity.setId(id);
            categoryEntity = categoryRepository.save(categoryEntity);

            return categoryMapper.update(categoryEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public void destroy(Long id) {
        Optional<CategoryEntity> categoryEntityOp = categoryRepository.findById(id);
        if (categoryEntityOp.isPresent()) {
            CategoryEntity categoryEntity = categoryEntityOp.get();
            categoryRepository.delete(categoryEntity);
            return;
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }
}
