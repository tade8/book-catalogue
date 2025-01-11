package com.org.managementservice.service;

import com.org.managementservice.*;
import com.org.managementservice.data.model.*;
import com.org.managementservice.data.repository.*;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import java.util.*;

@Component
@Validated
@Slf4j
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book createBook(@NotNull Book book) {
        book = bookRepository.save(book);
//        log.info("Book created: {}", book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
//        log.info("All books: {}", books);
        return books;
    }

    @Override
    public Book updateBook(@Valid @NotNull Book book) {
       Book foundBook = bookRepository.findById(book.getId()).
                orElseThrow(() -> new RuntimeException("Book not found"));
       foundBook = bookMapper.updateBook(book, foundBook);
       return bookRepository.save(foundBook);
    }

    @Override
    public String deleteBook(@NotNull String bookId) {
        bookRepository.deleteById(bookId);
        return String.format("Book with ID %s deleted successfully", bookId);
    }
}
