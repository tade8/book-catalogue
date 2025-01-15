package com.org.library.service;

import com.org.library.data.model.*;
import com.org.library.data.repository.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

import javax.validation.*;
import java.math.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
@ContextConfiguration(classes = {BookServiceImpl.class})
class BookServiceImplTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private Long bookId;

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
        if (bookId != null) {
            try {
                bookService.deleteBook(bookId);
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
    void createBookWithEmptyName() {
        book.setName("");
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(book));
    }

    @Test
    void createBookWithNullName() {
        book.setName(null);
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(book));
    }

    @Test
    void createBookWithEmptyIsbn() {
        book.setIsbn("");
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(book));
    }

    @Test
    @Order(3)
    void viewBookById() {
        try {
            book = bookService.getBookById(bookId);
        } catch (BookException e) {
            log.error("Error finding book by" + bookId, e);
        }
        assertNotNull(book);
    }

    @Test
    void viewBookByNullId() {
        assertThrows(ConstraintViolationException.class, ()->bookService.getBookById(null));
    }

    @Test
    @Order(4)
    void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    @Order(5)
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
        assertThrows(BookException.class, () -> bookService.updateBook(book));
    }

    @Test
    @Order(6)
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