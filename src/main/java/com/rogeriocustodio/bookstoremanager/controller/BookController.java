package com.rogeriocustodio.bookstoremanager.controller;

import com.rogeriocustodio.bookstoremanager.dto.BookDTO;
import com.rogeriocustodio.bookstoremanager.dto.MessageResponseDTO;
import com.rogeriocustodio.bookstoremanager.entity.Book;
import com.rogeriocustodio.bookstoremanager.mapper.BookMapper;
import com.rogeriocustodio.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) {
        Book book = bookMapper.toModel(bookDTO);

        return bookService.create(book);
    }

}
