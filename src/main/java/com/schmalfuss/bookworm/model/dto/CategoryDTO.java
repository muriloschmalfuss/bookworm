package com.schmalfuss.bookworm.model.dto;

import com.schmalfuss.bookworm.model.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;

    public CategoryDTO update(CategoryEntity categoryDTO) {
        this.id = categoryDTO.getId();
        this.name = categoryDTO.getName();
        this.description = categoryDTO.getDescription();
        return this;
    }
}
