package com.org.dtos;

import com.fasterxml.jackson.annotation.*;
import com.org.data.model.*;
import lombok.*;

import javax.validation.constraints.*;
import java.math.*;
import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookUpdateRequest {
    @NotNull(message = "The id must not be null")
    @Positive(message = "Book ID must be positive")
    private Long id;
    private String name;
    private String isbn;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;
    private BookType bookType;
    private BigDecimal price;
}
