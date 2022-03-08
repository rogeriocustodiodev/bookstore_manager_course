package com.rogeriocustodio.bookstoremanager.utils;

import com.rogeriocustodio.bookstoremanager.dto.AuthorDTO;
import com.rogeriocustodio.bookstoremanager.entity.Author;

import java.util.Random;
import java.util.UUID;

public class AuthorUtils {

    private static final Random FAKER = new Random();

    public static AuthorDTO createFakeAuthorDTO() {
        return AuthorDTO.builder()
                .id(FAKER.nextLong(100))
                .age(FAKER.nextInt(100))
                .name(UUID.randomUUID().toString())
                .build();
    }

    public static Author createFakeAuthor() {
        return Author.builder()
                .id(FAKER.nextLong(100))
                .age(FAKER.nextInt(100))
                .name(UUID.randomUUID().toString())
                .build();
    }
}
