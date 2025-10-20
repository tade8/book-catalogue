package com.org.webservice;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.servlet.*;

import javax.ws.rs.core.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks() throws Exception {
        this.mockMvc.perform(get("/books/")).
                andExpect(status().isOk());
    }

    @Test
    void createBook_ShouldReturnSuccess() throws Exception {
        mockMvc.perform(post("/books/create")
                        .param("name", "Clean Code")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/books/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-create-form"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void showEditForm() throws Exception {
        mockMvc.perform(get("/books/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books-list"));
    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(post("/books/edit/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Updated Title"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(get("/books/delete/1")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/books"));
    }
}