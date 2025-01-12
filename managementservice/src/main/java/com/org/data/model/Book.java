package com.org.data.model;

import com.fasterxml.jackson.annotation.*;
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
@Builder
@ToString
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private BigDecimal price;
}
