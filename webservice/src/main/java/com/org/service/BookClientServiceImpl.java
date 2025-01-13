package com.org.service;

import com.org.*;
import com.org.data.model.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import java.util.*;


@Service
@Slf4j
@Validated
@RequiredArgsConstructor
public class BookClientServiceImpl implements BookClientService {
    private final Client client = ClientBuilder.newClient();
    @Value("${backend.base.url}")
    private String baseUrl;

    @Override
    public Book createBook(@Valid @NotNull Book book) throws BookClientException {
        WebTarget target = client.target(baseUrl);
        Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        log.info("Request: {}", request);
        Response response = request.post(Entity.json(book));
        log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
        if (response.getStatus() != 200){
            throw new BookClientException(BookConstants.ERROR_CREATING_BOOK);
        }
        return response.readEntity(Book.class);
    }

    @Override
    public List<Book> getBooks() {
        Response response = client.target(baseUrl).request(MediaType.APPLICATION_JSON).get();
        log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
        return response.readEntity(new GenericType<List<Book>>(){});
    }

    @Override
    public Book updateBook(@Valid @NotNull Book book) throws BookClientException {
        Response response = client.target(baseUrl)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(book));
        if (response.getStatus() != 200){
            log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
            throw new BookClientException(BookConstants.ERROR_UPDATING_BOOK);
        }
        return response.readEntity(Book.class);
    }

    @Override
    public String deleteBook(@NotNull Long id) {
        Response response = client.target(baseUrl + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
        return response.readEntity(String.class);
    }

    @Override
    public Book getBookById(@NotNull Long id) throws BookClientException {
        try {
            Response response = client.target(baseUrl + "/" + id).request(MediaType.APPLICATION_JSON).get();
            log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
            return response.readEntity(Book.class);
        }
        catch (ProcessingException e) {
            log.error(BookConstants.ERROR_FETCHING_BOOK, e);
            throw new BookClientException(BookConstants.BOOK_NOT_FOUND);
        }
    }
}
