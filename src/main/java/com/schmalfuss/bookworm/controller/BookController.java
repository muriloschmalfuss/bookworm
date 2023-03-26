package com.schmalfuss.bookworm.controller;

import com.schmalfuss.bookworm.model.dto.BookDTO;
import com.schmalfuss.bookworm.model.dto.MessageDTO;
import com.schmalfuss.bookworm.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Object> list() {
        try {
            return ResponseEntity.ok(bookService.getAll());
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
            return ResponseEntity.ok(bookService.getById(id));
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
    public ResponseEntity<Object> create(@RequestBody @Valid BookDTO bookDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@RequestBody @Valid BookDTO bookDTO, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(bookService.edit(bookDTO, id));
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
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        try {
            bookService.destroy(id);
            return ResponseEntity.ok(new MessageDTO("Livro removido com sucesso"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<Object> findBooksByCategory(@PathVariable Long category_id) {
        try {
            return ResponseEntity.ok(bookService.listByCategory(category_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/publisher/{publisher_id}")
    public ResponseEntity<Object> findBooksByPublisher(@PathVariable Long publisher_id) {
        try {
            return ResponseEntity.ok(bookService.listByPublisher(publisher_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> filter(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "isbn", defaultValue = "") String isbn
    ) {
        try {
            return ResponseEntity.ok(bookService.filter(name, isbn));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(e.getMessage()));
        }
    }
}
