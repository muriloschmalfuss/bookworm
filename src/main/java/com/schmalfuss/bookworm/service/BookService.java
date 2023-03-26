package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.BookDTO;
import com.schmalfuss.bookworm.model.entity.BookEntity;
import com.schmalfuss.bookworm.model.mapper.BookMapper;
import com.schmalfuss.bookworm.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        List<BookEntity> entityList = bookRepository.findAll();
        return entityList.stream().map(bookEntity -> bookMapper.update(bookEntity)).toList();
    }

    public BookDTO getById(Long id) {
        Optional<BookEntity> bookEntityOp = bookRepository.findById(id);
        if (bookEntityOp.isPresent()) {
            BookEntity bookEntity = bookEntityOp.get();
            return bookMapper.update(bookEntity);
        }

        throw new EntityNotFoundException("Livro não encontrado");
    }

    public BookDTO create(BookDTO bookDTO) {
        BookEntity book = bookMapper.update(bookDTO);

        bookRepository.save(book);
        return bookMapper.update(book);
    }

    public BookDTO edit(BookDTO bookDTO, Long id) {
        if(bookRepository.existsById(id)) {
            BookEntity bookEntity = bookMapper.update(bookDTO);
            bookEntity.setId(id);
            bookEntity = bookRepository.save(bookEntity);

            return bookMapper.update(bookEntity);
        }

        throw new EntityNotFoundException("Livro não encontrado");
    }

    public void destroy(Long id) {
        Optional<BookEntity> bookEntityOp = bookRepository.findById(id);
        if (bookEntityOp.isPresent()) {
            BookEntity bookEntity = bookEntityOp.get();
            bookRepository.delete(bookEntity);
            return;
        }

        throw new EntityNotFoundException("Livro não encontrado");
    }
}
