package com.org.library;

import com.org.library.data.model.*;
import com.org.library.dtos.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    Book updateBook(Book book, @MappingTarget Book existingBook);
    Book toBook(BookCreateRequest bookCreateRequest);
    Book toBook(BookUpdateRequest bookUpdateRequest);
}
