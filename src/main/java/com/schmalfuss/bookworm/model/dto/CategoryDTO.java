package com.schmalfuss.bookworm.model.dto;

import com.schmalfuss.bookworm.model.entity.BookEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    @Size(max = 100, message = "Tamanho do nome acima do permitido")
    @NotBlank(message = "Nome deve conter algum valor")
    private String name;
    private String description;
}
