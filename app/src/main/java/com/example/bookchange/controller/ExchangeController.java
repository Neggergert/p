package com.example.bookchange.controller;

import com.example.bookchange.model.Book;
import com.example.bookchange.model.Exchange;
import com.example.bookchange.repository.BookRepository;
import com.example.bookchange.repository.ExchangeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {
    private final ExchangeRepository exchangeRepository;
    private final BookRepository bookRepository;

    public ExchangeController(ExchangeRepository exchangeRepository, BookRepository bookRepository) {
        this.exchangeRepository = exchangeRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Exchange> list() {
        return exchangeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exchange create(@RequestParam Long bookId,
                           @RequestParam String fromUser,
                           @RequestParam String toUser) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book not available");
        }
        book.setAvailable(false);
        bookRepository.save(book);

        Exchange ex = new Exchange();
        ex.setBook(book);
        ex.setFromUser(fromUser);
        ex.setToUser(toUser);
        return exchangeRepository.save(ex);
    }
}
