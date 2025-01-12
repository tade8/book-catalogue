package com.org.service;

import com.org.data.model.*;

import java.util.*;

public interface BookClientService {
    Book createBook(Book book);
    List<Book> getBooks();
    Book updateBook(Book book);
    String deleteBook(String id);
}
