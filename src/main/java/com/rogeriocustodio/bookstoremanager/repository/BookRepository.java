package com.rogeriocustodio.bookstoremanager.repository;

import com.rogeriocustodio.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
