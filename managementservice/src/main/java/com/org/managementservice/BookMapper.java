package com.org.managementservice;

import com.org.managementservice.data.model.*;
import com.org.managementservice.dtos.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    Book updateBook(Book book, @MappingTarget Book existingBook);
    Book toBook(BookCreateRequest bookCreateRequest);
    Book toBook(BookUpdateRequest bookUpdateRequest);
}
