package com.org.managementservice.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;
import java.time.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String isbn;
    private LocalDate publishedDate;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
}
