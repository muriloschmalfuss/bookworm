package com.schmalfuss.bookworm.model.dto;

import com.schmalfuss.bookworm.model.entity.CategoryEntity;
import com.schmalfuss.bookworm.model.entity.PublisherEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;

    private CategoryEntity category;

    private PublisherEntity publisher;

    @NotBlank(message = "Nome deve conter algum valor")
    private String name;

    @Size(max = 13)
    @NotBlank(message = "ISBN deve conter algum valor")
    private String isbn;
}
