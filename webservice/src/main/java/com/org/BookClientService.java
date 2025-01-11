package com.org;

import com.org.data.model.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;


@Component

public class BookClientService {
    @Value("${backend.base.url}")
    private String baseUrl;
    private final Client client = ClientBuilder.newClient();


    public List<Book> getBooks() {
        Response response = client.target(baseUrl).request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(new GenericType<List<Book>>());
    }
}
