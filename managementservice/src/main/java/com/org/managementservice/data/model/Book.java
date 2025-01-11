package com.org.managementservice.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.*;
import java.time.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull(message = "The id of the book must not be null")
    private String id;
    @Column(unique = true, nullable = false)
    @NotNull(message = "The name must not be null")
    private String name;
    @Column(unique = true, nullable = false)
    @NotNull(message = "The isbn book must not be null")
    private String isbn;
    private LocalDate publishedDate;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private BigDecimal price;
}
