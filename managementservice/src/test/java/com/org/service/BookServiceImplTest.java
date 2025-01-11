package com.org.service;

import com.org.data.model.*;
import com.org.data.repository.*;
import com.org.managementservice.data.model.*;
import com.org.managementservice.data.repository.*;
import jakarta.validation.*;
import lombok.extern.slf4j.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.math.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class BookServiceImplTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private String bookId;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("My Book");
        book.setBookType(BookType.E_BOOK);
        book.setIsbn("90057-36980");
        book.setPrice(new BigDecimal("5000.89"));
    }

    @AfterAll
    void tearDown() {
        if (book != null && StringUtils.isNotEmpty(book.getId())) {
            try {
                bookService.deleteBook(book.getId());
            } catch (BookException e) {
                log.info("Error deleting book: {}", e.getMessage());
            }
        }
    }

    @Test
    @Order(1)
    void createBook() {
        try {
            book = bookService.createBook(book);
        } catch (BookException e) {
            log.info("Error creating book: {}", e.getMessage());
        }

        assertNotNull(book);
        assertNotNull(book.getPublishedDate());
        assertNotNull(book.getId());
        bookId = book.getId();
    }

    @Test
    @Order(2)
    void createExistingBook() {
        Optional<Book> foundBook = bookRepository.findByName(book.getName());
        assertTrue(foundBook.isPresent());

        assertThrows(BookException.class, ()-> bookService.createBook(foundBook.get()));
    }

    @Test
    void createEmptyBook() {
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(null));
    }

    @Test
    @Order(3)
    void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    @Order(4)
    void editBook() {
        Optional<Book> foundBook = bookRepository.findById(bookId);
        assertTrue(foundBook.isPresent());

        foundBook.get().setName("Half of a yellow sun");
        Book updatedBook = null;
        try {
            updatedBook = bookService.updateBook(foundBook.get());
        } catch (BookException e) {
            log.info("Error updating book: {}", e.getMessage());
        }

        assertNotNull(updatedBook);
        assertNotEquals(updatedBook, book);
        assertEquals("Half of a yellow sun", updatedBook.getName());
    }

    @Test
    void editNullBook() {
        assertThrows(ConstraintViolationException.class, () -> bookService.updateBook(null));
    }

    @Test
    void editBookWithNullId() {
        book.setId(null);
        assertThrows(ConstraintViolationException.class, () -> bookService.updateBook(book));
    }

    @Test
    @Order(5)
    void deleteBook() {
        String response = null;
        try {
            response = bookService.deleteBook(bookId);
        } catch (BookException e) {
            log.info("Error deleting book: {}", e.getMessage());
        }

        assertEquals("Half of a yellow sun deleted successfully", response);
    }

    @Test
    void deleteBookWithNullId() {
        assertThrows(ConstraintViolationException.class, () -> bookService.deleteBook(null));
    }
}