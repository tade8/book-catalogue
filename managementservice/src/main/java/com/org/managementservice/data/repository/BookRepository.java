package com.org.managementservice.data.repository;

import com.org.managementservice.*;
import com.org.managementservice.data.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.validation.constraints.*;
import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(@NotNull(message = BookConstants.THE_NAME_MUST_NOT_BE_NULL) String name);
}
