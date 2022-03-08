package com.rogeriocustodio.bookstoremanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogeriocustodio.bookstoremanager.dto.BookDTO;
import com.rogeriocustodio.bookstoremanager.dto.MessageResponseDTO;
import com.rogeriocustodio.bookstoremanager.mapper.BookMapper;
import com.rogeriocustodio.bookstoremanager.service.BookService;
import com.rogeriocustodio.bookstoremanager.utils.BookUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String BOOK_API_URL_PATH = "/api/v1/books";
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(((viewName, locale) -> new MappingJackson2JsonView()))
                .build();
    }

    @Test
    void testWhenPostIsCalledThenABookShouldBeCreated() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID " + bookDTO.getId())
                .build();
        when(bookService.create(bookMapper.toModel(bookDTO)))
                .thenReturn(expectedMessageResponse);

        mockMvc.perform(post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void testWhenPostWithInvalidISBNIsCalledThenABadRequestShouldBeReturned() throws Exception {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        bookDTO.setIsbn("INVALID ISBN");

        mockMvc.perform(post(BOOK_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

}