package com.org.service;

import com.org.data.model.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.util.*;


@Service
//@RequiredArgsConstructor
public class BookClientServiceImpl implements BookClientService {
    private Client client = ClientBuilder.newClient();
    @Value("${backend.base.url}")
    private String baseUrl;

    @Override
    public Book createBook(Book book) {
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
        return response.readEntity(Book.class);
    }

    @Override
    public List<Book> getBooks() {
        Response response = client.target(baseUrl).request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Book>>(){});
    }

    @Override
    public Book updateBook(Book book) {
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(book));
        return response.readEntity(Book.class);
    }

    @Override
    public String deleteBook(String id) {
        Response response = client.target(baseUrl + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        return response.readEntity(String.class);
    }
}
