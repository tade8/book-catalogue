package com.org.managementservice.data.repository;

import com.org.managementservice.*;
import com.org.managementservice.data.model.*;
import jakarta.validation.constraints.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findByName(@NotNull(message = BookConstants.THE_NAME_MUST_NOT_BE_NULL) String name);
}
