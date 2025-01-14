package com.org.webservice;

import com.org.managementservice.*;
import com.org.managementservice.data.model.Book;
import com.org.managementservice.dtos.*;
import com.org.webservice.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookClientController {
    public static final String ERROR = "error";
    public static final String REDIRECT_BOOKS_LIST = "redirect:/books";
    public static final String BOOK_CREATE_FORM = "book-create-form";
    public static final String MESSAGE = "message";
    public static final String BOOK_EDIT_FORM = "book-edit-form";
    private final BookClientService bookClientService;
    private final BookMapper bookMapper;

    @Autowired
    public BookClientController(BookClientService bookClientService, BookMapper bookMapper) {
        this.bookClientService = bookClientService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        try {
            List<Book> books = bookClientService.getBooks();
            model.addAttribute("books", books);
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error fetching books: " + e.getMessage());
        }
        return "books-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookCreateRequest());
        return BOOK_CREATE_FORM;
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("book") BookCreateRequest bookCreateRequest,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return BOOK_CREATE_FORM;
        }
        Book book = bookMapper.toBook(bookCreateRequest);
        try {
            Book createdBook = bookClientService.createBook(book);
            log.info("Book created: " + createdBook);
            model.addAttribute(MESSAGE, "Book created successfully!");
            return REDIRECT_BOOKS_LIST;
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error creating book: " + e.getMessage());
            return BOOK_CREATE_FORM;
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Book book = bookClientService.getBookById(id);
            model.addAttribute("book", book);
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error fetching book details: " + e.getMessage());
            return "books-list";
        }
        return BOOK_EDIT_FORM;
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("book") BookUpdateRequest bookUpdateRequest,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return BOOK_EDIT_FORM;
        }
        Book book = bookMapper.toBook(bookUpdateRequest);
        book.setId(id);
        log.info("Book update request: " + book);
        try {
            Book updatedBook = bookClientService.updateBook(book);
            log.info("Book updated: {}", updatedBook);
            model.addAttribute(MESSAGE, "Book updated successfully!");
            return REDIRECT_BOOKS_LIST;
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error updating book: " + e.getMessage());
            return BOOK_EDIT_FORM;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookClientService.deleteBook(id);
            model.addAttribute(MESSAGE, "Book deleted successfully!");
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error deleting book: " + e.getMessage());
        }
        return REDIRECT_BOOKS_LIST;
    }
}
