package com.org.managementservice.data.repository;

import com.org.managementservice.data.model.*;
import org.springframework.data.jpa.repository.*;

public interface BookRepository extends JpaRepository<Book, String> {
}
