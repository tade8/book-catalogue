package com.org.service;

import com.org.data.model.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class BookClientServiceImpl implements BookClientService {
    private final Client client = ClientBuilder.newClient();
    @Value("${backend.base.url}")
    private String baseUrl;

    @Override
    public Book createBook(Book book) throws BookClientException {
        Response response;
        try {
            response = client.target(baseUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(book, MediaType.APPLICATION_JSON));
            return response.readEntity(Book.class);
        } catch (ProcessingException e) {
            log.error("Error creating book: {}", e.getMessage());
            throw new BookClientException("Cannot connect to server");
        }
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
