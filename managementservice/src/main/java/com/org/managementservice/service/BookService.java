package com.org.managementservice.service;

import com.org.managementservice.data.model.*;

import java.util.*;

public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBooks();
    Book updateBook(Book book);
    String deleteBook(Book book);
}
