package com.org;

import com.org.data.model.*;
import com.org.managementservice.data.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    Book updateBook(Book book, @MappingTarget Book existingBook);
}
