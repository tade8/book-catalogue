package com.org;

import com.org.data.model.*;
import com.org.dtos.*;
import com.org.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookClientController {
    public static final String ERROR = "error";
    public static final String REDIRECT_BOOKS = "redirect:/books";
    private final BookClientService bookClientService;
    private final BookMapper bookMapper;

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
        return "book-form";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("book") BookCreateRequest bookCreateRequest,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-form";
        }
        Book book = bookMapper.toBook(bookCreateRequest);
        try {
            Book createdBook = bookClientService.createBook(book);
            log.info("Book created: " + createdBook);
            model.addAttribute("message", "Book created successfully!");
            return REDIRECT_BOOKS;
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error creating book: " + e.getMessage());
            return "book-form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Book book = bookClientService.getBookById(id);
            model.addAttribute("book", book);
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error fetching book details: " + e.getMessage());
            return "book-list";
        }
        return "book-edit-form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("book") BookUpdateRequest bookUpdateRequest, 
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-edit-form";
        }
        Book book = bookMapper.toBook(bookUpdateRequest);
        try {
            Book updatedBook = bookClientService.updateBook(book);
            log.info("Book updated: {}", updatedBook);
            model.addAttribute("message", "Book updated successfully!");
            return REDIRECT_BOOKS;
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error updating book: " + e.getMessage());
            return "book-edit-form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            bookClientService.deleteBook(id);
            model.addAttribute("message", "Book deleted successfully!");
        } catch (Exception e) {
            model.addAttribute(ERROR, "Error deleting book: " + e.getMessage());
        }
        return REDIRECT_BOOKS;
    }
}
