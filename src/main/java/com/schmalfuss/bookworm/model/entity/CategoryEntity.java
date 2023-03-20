package com.schmalfuss.bookworm.model.entity;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIES")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    public CategoryEntity update(CategoryDTO categoryDTO) {
        this.name = categoryDTO.getName();
        this.description = categoryDTO.getDescription();
        return this;
    }

}
