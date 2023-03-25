package com.schmalfuss.bookworm.model.mapper;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
import com.schmalfuss.bookworm.model.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDTO update(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setDescription(categoryEntity.getDescription());
        return categoryDTO;
    }

    public CategoryEntity update(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
        return categoryEntity;
    }

    public List<CategoryEntity> updateListEntity(List<CategoryDTO> categoryDTOList) {
        return categoryDTOList.stream().map(this::update).toList();
    }

    public List<CategoryDTO> updateListDTO(List<CategoryEntity> categoryEntityList) {
        return categoryEntityList.stream().map(this::update).toList();
    }
}
