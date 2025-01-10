package com.org.managementservice.service;

import com.org.managementservice.data.model.*;
import com.org.managementservice.data.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public String deleteBook(Book book) {
        return String.format("%s deleted successfully", book.getName());
    }
}
