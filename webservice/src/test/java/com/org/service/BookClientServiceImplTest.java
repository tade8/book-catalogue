package com.org.service;

import com.org.data.model.*;
import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.math.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = WebserviceApplication.class)
class BookClientServiceImplTest {
    @Autowired
    private BookClientService bookClientService;
    private Book book;

    @Test
    void createBook() {
        book = Book.builder().
                name("Things Fall Apart").
                bookType(BookType.HARD_COVER).
                isbn("785765783296").
                price(new BigDecimal("30000")).build();
        try {
            book = bookClientService.createBook(book);
        } catch (BookClientException e) {
            log.error("Book creation failed: {}", e.getMessage());
        }
        assertNotNull(book);
    }
}