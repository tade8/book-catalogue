package com.org.service;

import com.org.*;
import com.org.data.model.*;
import com.org.data.repository.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.*;

import javax.validation.*;
import java.math.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = WebserviceApplication.class)
class BookClientServiceImplTest {
    @Autowired
    private BookClientService bookClientService;
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private Long bookId;

    @BeforeEach
    void setUp() {
        book = Book.builder().
                name("Things Fall Apart").
                bookType(BookType.HARD_COVER).
                isbn("785765783296").
                price(new BigDecimal("30000")).build();
    }

    @AfterEach
    void cleanUp() throws BookClientException {
        if (bookId != null) {
            bookClientService.deleteBook(bookId);
        }
    }

    @Test
    void createBook() {
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: ", e);
        }
        assertNotNull(book);
        assertNotNull(book.getId());
        bookId = book.getId();
    }

    @Test
    void createExistingBook() {
        Book foundBook = bookRepository.findByName(book.getName()).orElse(null);
        assertNull(foundBook);
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: ", e);
        }
        assertNotNull(book);
        assertNotNull(book.getId());
        bookId = book.getId();

        assertThrows(BookClientException.class, () -> bookClientService.createBook(book));
    }

    @Test
    void createBookWithNullName() {
        book = Book.builder().
                name(null).
                bookType(BookType.HARD_COVER).
                isbn("785765783296").
                price(new BigDecimal("30000")).build();

        assertThrows(ConstraintViolationException.class, () -> bookClientService.createBook(book));
    }

    @Test
    void updateBook() throws BookClientException {
        Book foundBook = bookRepository.findByName(book.getName()).orElse(null);
        assertNull(foundBook);
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: ", e);
        }
        assertNotNull(book);
        assertNotNull(book.getId());

        book = bookClientService.updateBook(book);
        assertNotNull(book);
        bookId = book.getId();
    }

    @Test
    void updateNonExistingBook() {
        assertThrows(BookClientException.class, () -> bookClientService.updateBook(book));
    }

    @Test
    void deleteBook() throws BookClientException {
        Book foundBook = bookRepository.findByName(book.getName()).orElse(null);
        assertNull(foundBook);
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: ", e);
        }
        assertNotNull(book);
        assertNotNull(book.getId());
        bookId = book.getId();

        String response = bookClientService.deleteBook(bookId);
        assertEquals("Things Fall Apart deleted successfully", response);
    }

    @Test
    void viewAllBooks() {
        Book foundBook = bookRepository.findByName(book.getName()).orElse(null);
        assertNull(foundBook);
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: ", e);
        }
        assertNotNull(book);
        assertNotNull(book.getId());
        bookId = book.getId();

        List<Book> books = bookClientService.getBooks();
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    void deleteBookWithEmptyId() {
        assertThrows(ConstraintViolationException.class,  ()->bookClientService.deleteBook(null));
    }

    @AfterAll
    void tearDown() throws BookClientException {
        if (bookId != null) {
            bookClientService.deleteBook(bookId);
        }
    }
}