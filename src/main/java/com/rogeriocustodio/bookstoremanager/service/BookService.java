package com.rogeriocustodio.bookstoremanager.service;

import com.rogeriocustodio.bookstoremanager.dto.MessageResponseDTO;
import com.rogeriocustodio.bookstoremanager.entity.Book;
import com.rogeriocustodio.bookstoremanager.exception.BookNotFoundException;
import com.rogeriocustodio.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public MessageResponseDTO create(Book book) {
        Book savedBook = bookRepository.save(book);

        return MessageResponseDTO.builder()
                .message("Book created with ID " + savedBook.getId())
                .build();
    }

    public Book findById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}

