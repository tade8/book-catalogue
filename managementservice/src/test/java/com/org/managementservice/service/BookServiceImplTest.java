package com.org.managementservice.service;

import com.org.managementservice.data.model.*;
import jakarta.validation.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.math.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookServiceImplTest {
    @Autowired
    private BookService bookService;
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("My Book");
        book.setBookType(BookType.E_BOOK);
        book.setIsbn("90057-36980");
        book.setPrice(new BigDecimal("5000.89"));
        book.setPublishedDate(LocalDate.of(2024, 2, 3));
    }

    @AfterEach
    void tearDown() {
        if (book != null && book.getId() != null) {
            bookService.deleteBook(book.getId());
        }
    }

    @Test
    void createBook() {
        book = bookService.createBook(book);

        assertNotNull(book);
        assertNotNull(book.getId());
    }

    @Test
    void createEmptyBook() {
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(null));
    }

    @Test
    void viewAllBooks() {
        book.setName("Things Fall Apart");
        book.setIsbn("86431-09675");
        book = bookService.createBook(book);
        assertNotNull(book);

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    void editBook() {
        book.setName("Half a yellow sun");
        book.setIsbn("64566-074762");
        book = bookService.createBook(book);
        assertNotNull(book);

        book.setName("Half of a yellow sun");
        Book updatedBook = bookService.updateBook(book);

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
        ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () -> bookService.updateBook(book));
        log.info("Exception message: {}", exception.getMessage());
    }

    @Test
    void deleteBookWithNullId() {
        assertThrows(ConstraintViolationException.class, () -> bookService.deleteBook(null));
    }
}