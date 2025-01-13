package com.org.controller;

import com.org.*;
import com.org.data.model.*;
import com.org.dtos.*;
import com.org.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) throws BookException {
        Book book = bookMapper.toBook(bookCreateRequest);
        return bookService.createBook(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping
    public Book updateBook(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) throws BookException {
        Book book = bookMapper.toBook(bookUpdateRequest);
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@Valid @PathVariable
                                 @NotNull(message = "The id must not be null")
                                 @NotBlank(message = "The id must not be empty")
                                 Long id) throws BookException {
        return bookService.deleteBook(id);
    }
}
