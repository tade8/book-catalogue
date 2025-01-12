package com.org.service;

import com.org.*;
import com.org.data.model.*;
import com.org.data.repository.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.time.*;
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
    public Book createBook(@NotNull Book book) throws BookException {
        Optional<Book> foundBook = bookRepository.findByName(book.getName());
        if (foundBook.isPresent()) {
            throw new BookException(BookConstants.THIS_BOOK_ALREADY_EXISTS);
        }
        book.setPublishedDate(LocalDate.now());
        book = bookRepository.save(book);
        log.info("Book created: {}", book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("All books: {}", books);
        return books;
    }

    @Override
    public Book updateBook(@Valid @NotNull Book book) throws BookException {
       Book foundBook = bookRepository.findById(book.getId()).
                orElseThrow(() -> new BookException(BookConstants.BOOK_NOT_FOUND));
       foundBook = bookMapper.updateBook(book, foundBook);
       foundBook = bookRepository.save(foundBook);
       log.info("Book updated: {}", foundBook);
       return foundBook;
    }

    @Override
    public String deleteBook(@NotNull Long bookId) throws BookException {
        Book foundBook = bookRepository.findById(bookId).
                orElseThrow(() -> new BookException(BookConstants.BOOK_NOT_FOUND));
        bookRepository.delete(foundBook);
        return String.format("%s deleted successfully", foundBook.getName());
    }
}
