package com.schmalfuss.bookworm.model.mapper;

import com.schmalfuss.bookworm.model.dto.BookDTO;
import com.schmalfuss.bookworm.model.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO update(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setName(bookEntity.getName());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setCategory(bookEntity.getCategory());
        bookDTO.setPublisher(bookEntity.getPublisher());
        return bookDTO;
    }

    public BookEntity update(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setName(bookDTO.getName());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setCategory(bookDTO.getCategory());
        bookEntity.setPublisher(bookDTO.getPublisher());
        return bookEntity;
    }
}
