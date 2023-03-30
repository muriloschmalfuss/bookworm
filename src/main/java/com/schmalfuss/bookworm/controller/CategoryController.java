package com.schmalfuss.bookworm.controller;

import com.schmalfuss.bookworm.model.dto.CategoryDTO;
import com.schmalfuss.bookworm.model.dto.MessageDTO;
import com.schmalfuss.bookworm.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> list() {
        try {
            return ResponseEntity.ok(categoryService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoryService.getById(id));
        } catch (EntityActionVetoException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping
    @Secured("admin")
    public ResponseEntity<Object> create(@RequestBody @Valid CategoryDTO categoryDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Secured("admin")
    public ResponseEntity<Object> edit(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(categoryService.edit(categoryDTO, id));
        } catch (EntityActionVetoException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageDTO(e.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Secured("admin")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        try {
            categoryService.destroy(id);
            return ResponseEntity.ok(new MessageDTO("Categoria removida com sucesso"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }
}
