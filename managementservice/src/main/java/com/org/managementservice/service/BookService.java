package com.org.managementservice.service;

import com.org.managementservice.data.model.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

public interface BookService {
    Book createBook(@Valid @NotNull Book book) throws BookException;
    List<Book> getAllBooks();
    Book getBookById(@NotNull Long id) throws BookException;
    Book updateBook(@Valid @NotNull Book book) throws BookException;
    String deleteBook(@NotNull Long bookId) throws BookException;
}
