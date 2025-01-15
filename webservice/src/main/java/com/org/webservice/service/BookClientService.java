package com.org.webservice.service;

import com.org.library.data.model.*;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;

public interface BookClientService {
    Book createBook(@Valid @NotNull Book book) throws BookClientException;
    List<Book> getBooks();
    Book updateBook(@Valid @NotNull Book book) throws BookClientException;
    String deleteBook(@NotNull Long id) throws BookClientException;
    Book getBookById(@NotNull Long id) throws BookClientException;
}
