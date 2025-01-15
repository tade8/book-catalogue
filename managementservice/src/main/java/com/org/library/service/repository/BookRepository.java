package com.org.library.service.repository;

import com.org.library.*;
import com.org.library.data.model.*;
import org.springframework.data.jpa.repository.*;

import javax.validation.constraints.*;
import java.util.*;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(@NotNull(message = BookConstants.THE_NAME_MUST_NOT_BE_NULL) String name);
}
