package com.org.managementservice.service;

import com.org.managementservice.data.model.*;
import com.org.managementservice.data.repository.*;
import jakarta.validation.*;
import lombok.extern.slf4j.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.math.*;
import java.time.*;
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
        book.setPublishedDate(LocalDate.of(2024, 2, 3));
    }

    @AfterAll
    void tearDown() {
        if (book != null && StringUtils.isNotEmpty(book.getId())) {
            bookService.deleteBook(book.getId());
        }
    }

    @Test
    @Order(1)
    void createBook() {
        book = bookService.createBook(book);

        assertNotNull(book);
        bookId = book.getId();
        assertNotNull(bookId);
    }

    @Test
    void createEmptyBook() {
        assertThrows(ConstraintViolationException.class, ()->bookService.createBook(null));
    }

    @Test
    @Order(2)
    void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    @Order(3)
    void editBook() {
        Optional<Book> foundBook = bookRepository.findById(bookId);
        assertTrue(foundBook.isPresent());

        foundBook.get().setName("Half of a yellow sun");
        Book updatedBook = bookService.updateBook(foundBook.get());

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
    @Order(4)
    void deleteBook() {
        String response = bookService.deleteBook(bookId);

        assertEquals("Half of a yellow sun deleted successfully", response);
    }

    @Test
    void deleteBookWithNullId() {
        assertThrows(ConstraintViolationException.class, () -> bookService.deleteBook(null));
    }
}