package com.org.controller;

import com.org.data.model.*;
import com.org.managementservice.data.model.*;
import com.org.managementservice.service.*;
import com.org.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) throws BookException {
        return bookService.createBook(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) throws BookException {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) throws BookException {
        return bookService.deleteBook(id);
    }
}
