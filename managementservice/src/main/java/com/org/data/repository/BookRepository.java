package com.org.data.repository;

import com.org.*;
import com.org.data.model.*;
import com.org.managementservice.data.model.*;
import jakarta.validation.constraints.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findByName(@NotNull(message = BookConstants.THE_NAME_MUST_NOT_BE_NULL) String name);
}
