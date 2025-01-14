package com.org.managementservice.data.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Long id;
    @Column(unique = true, nullable = false)
    @NotNull(message = "The name must not be null")
    @NotBlank(message = "The name must not be empty")
    private String name;
    @Column(unique = true, nullable = false)
    @NotNull(message = "The isbn must not be null")
    @NotBlank(message = "The isbn must not be empty")
    private String isbn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private BigDecimal price;
}
