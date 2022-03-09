package com.rogeriocustodio.bookstoremanager.service;

import com.rogeriocustodio.bookstoremanager.entity.Book;
import com.rogeriocustodio.bookstoremanager.exception.BookNotFoundException;
import com.rogeriocustodio.bookstoremanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.rogeriocustodio.bookstoremanager.utils.BookUtils.createFakeBook;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        // Arrange
        Book expectedFoundBook = createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        // Act
        Book returnedBook = bookService.findById(expectedFoundBook.getId());

        // Assert
        assertEquals(expectedFoundBook, returnedBook);
    }

    @Test
    void whenGivenUnexistingIdThenReturnNotFoundAndThrownAnException() {
        // Arrange
        var invalidId = 10L;
        when(bookRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }
}