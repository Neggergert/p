package com.example.bookchange.repository;

import com.example.bookchange.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailableTrueAndTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}
