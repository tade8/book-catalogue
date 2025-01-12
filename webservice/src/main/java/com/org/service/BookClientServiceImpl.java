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
        Response response;
        try {
            WebTarget target = client.target(baseUrl);
            Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
            log.info("Request : {}", request);
            response = request.post(Entity.json(book));
            log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
            return response.readEntity(Book.class);
        }
        catch (ProcessingException e) {
            log.error("Error creating book: {}", e.getMessage());
            throw new BookClientException(BookConstants.INVALID_INPUT_PROVIDED);
        }
    }

    @Override
    public List<Book> getBooks() {
        Response response = client.target(baseUrl).request(MediaType.APPLICATION_JSON).get();
        log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
        return response.readEntity(new GenericType<List<Book>>(){});
    }

    @Override
    public Book updateBook(@Valid @NotNull Book book) throws BookClientException {
        try {
            Response response = client.target(baseUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(book));
            log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
            return response.readEntity(Book.class);
        } catch (ProcessingException e) {
            throw new BookClientException(BookConstants.INVALID_INPUT_PROVIDED);
        }
    }

    @Override
    public String deleteBook(@NotNull Long id) throws BookClientException {
        try {
            Response response = client.target(baseUrl).path(id.toString())
                    .request(MediaType.APPLICATION_JSON)
                    .delete();
            log.info(BookConstants.RESPONSE_RETURNED + ": {}", response);
            return response.readEntity(String.class);
        } catch (ProcessingException e) {
            log.error("Error deleting book: {}", e.getMessage());
            throw new BookClientException("Error deleting book");
        }
    }
}
