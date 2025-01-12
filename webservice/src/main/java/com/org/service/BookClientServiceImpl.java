package com.org.service;

import com.org.data.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.util.*;


@Service
public class BookClientServiceImpl implements BookClientService {
    @Value("${backend.base.url}")
    private String baseUrl;
    private Client client = ClientBuilder.newClient();


    public Book createBook(Book book) {
        try (Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON))) {
            return response.readEntity(Book.class);
        }
    }

    public List<Book> getBooks() {
        Response response = client.target(baseUrl).request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Book>>(){});
    }

    public Book updateBook(Book book) {
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(book));
        return response.readEntity(Book.class);
    }

    public String deleteBook(String id) {
        Response response = client.target(baseUrl + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        return response.readEntity(String.class);
    }
}
