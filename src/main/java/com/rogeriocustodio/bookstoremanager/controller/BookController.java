package com.rogeriocustodio.bookstoremanager.controller;

import com.rogeriocustodio.bookstoremanager.dto.BookRequest;
import com.rogeriocustodio.bookstoremanager.dto.MessageResponseDTO;
import com.rogeriocustodio.bookstoremanager.entity.Book;
import com.rogeriocustodio.bookstoremanager.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public MessageResponseDTO create(@RequestBody BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);

        return bookService.create(book);
    }

}
