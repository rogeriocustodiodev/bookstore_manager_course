package com.rogeriocustodio.bookstoremanager.utils;

import com.rogeriocustodio.bookstoremanager.dto.AuthorDTO;
import com.rogeriocustodio.bookstoremanager.dto.BookDTO;
import com.rogeriocustodio.bookstoremanager.entity.Book;

import java.util.Random;
import java.util.UUID;

import static com.rogeriocustodio.bookstoremanager.utils.AuthorUtils.createFakeAuthor;
import static com.rogeriocustodio.bookstoremanager.utils.AuthorUtils.createFakeAuthorDTO;

public class BookUtils {

    private static final Random FAKER = new Random();

    public static BookDTO createFakeBookDTO() {
        return BookDTO.builder()
                .id(FAKER.nextLong(100))
                .name(UUID.randomUUID().toString())
                .pages(FAKER.nextInt(200))
                .chapters(FAKER.nextInt(20))
                .isbn("0-596-52068-9")
                .publisherName(UUID.randomUUID().toString())
                .author(createFakeAuthorDTO())
                .build();
    }

    public static Book createFakeBook() {
        return Book.builder()
                .id(FAKER.nextLong(100))
                .name(UUID.randomUUID().toString())
                .pages(FAKER.nextInt(200))
                .chapters(FAKER.nextInt(20))
                .isbn("0-596-52068-9")
                .publisherName(UUID.randomUUID().toString())
                .author(createFakeAuthor())
                .build();
    }
}
