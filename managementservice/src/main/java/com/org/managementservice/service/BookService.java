package com.org.managementservice.service;

import com.org.managementservice.data.model.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

import java.util.*;

public interface BookService {
    Book createBook(@NotNull Book book) throws BookException;
    List<Book> getAllBooks();
    Book updateBook(@Valid @NotNull Book book) throws BookException;
    String deleteBook(@NotNull String bookId) throws BookException;
}
