package com.org.library.service;

import com.org.library.data.model.*;
import com.org.library.data.repository.*;
import com.org.library.mapper.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.math.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("My Book");
        book.setBookType(BookType.E_BOOK);
        book.setIsbn("90057-36980");
        book.setPrice(new BigDecimal("5000.89"));
    }

    @Test
    void createBook() {
        try {
            when(bookRepository.save(any())).thenReturn(book);
            book = bookService.createBook(book);
        } catch (BookException e) {
            log.info("Error creating book: {}", e.getMessage());
        }
        assertNotNull(book);
        assertNotNull(book.getPublishedDate());
    }

    @Test
    void createExistingBook() {
        when(bookRepository.findByName(book.getName())).thenReturn(Optional.of(book));
        assertThrows(BookException.class, ()-> bookService.createBook(book));
    }

    @Test
    @Order(3)
    void viewBookById() {
        try {
            book = bookService.getBookById(1L);
        } catch (BookException e) {
            log.error("Error finding book by ID");
        }
        assertNotNull(book);
    }

    @Test
    void viewBookByNullId() {
        assertThrows(BookException.class, ()->bookService.getBookById(null));
    }

    @Test
    void viewAllBooks() {
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(books);
        books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    void editBook() {
        Book updatedBook = new Book();
        try {
            book.setId(1L);
            book.setName("Half of a yellow sun");

            when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
            when(bookMapper.updateBook(any(), any())).thenReturn(updatedBook);
            when(bookRepository.save(any())).thenReturn(updatedBook);
            updatedBook = bookService.updateBook(book);
        } catch (BookException e) {
            log.info("Error updating book: {}", e.getMessage());
        }

        assertNotNull(updatedBook);
        assertNotEquals(book.getName(), updatedBook.getName());
    }

    @Test
    void editBookWithNullId() {
        book.setId(null);
        assertThrows(BookException.class, () -> bookService.updateBook(book));
    }

    @Test
    void deleteBook() {
        String response = null;
        try {
            when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
            response = bookService.deleteBook(anyLong());
        } catch (BookException e) {
            log.info("Error deleting book: {}", e.getMessage());
        }
        assertEquals("My Book deleted successfully", response);
    }
}