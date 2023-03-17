package com.schmalfuss.bookworm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private Integer id;
    private String name;
    private String description;

    public Category update(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
        return this;
    }
}
