package com.rogeriocustodio.bookstoremanager.controller;

import com.rogeriocustodio.bookstoremanager.dto.BookDTO;
import com.rogeriocustodio.bookstoremanager.dto.MessageResponseDTO;
import com.rogeriocustodio.bookstoremanager.entity.Book;
import com.rogeriocustodio.bookstoremanager.exception.BookNotFoundException;
import com.rogeriocustodio.bookstoremanager.mapper.BookMapper;
import com.rogeriocustodio.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.toModel(bookDTO);

        return bookService.create(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws BookNotFoundException {
        Book book = bookService.findById(id);

        return ResponseEntity.ok().body(bookMapper.toDTO(book));
    }

}
