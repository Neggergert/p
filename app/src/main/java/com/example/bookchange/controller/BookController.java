package com.example.bookchange.controller;

import com.example.bookchange.model.Book;
import com.example.bookchange.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> list(@RequestParam(defaultValue = "") String title,
                           @RequestParam(defaultValue = "") String author) {
        return repository.findByAvailableTrueAndTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        book.setAvailable(true);
        return repository.save(book);
    }
}
