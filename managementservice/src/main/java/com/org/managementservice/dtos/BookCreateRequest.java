package com.org.managementservice.dtos;

import com.org.managementservice.data.model.*;
import lombok.*;

import javax.validation.constraints.*;
import java.math.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BookCreateRequest {
    @NotNull(message = "The name must not be null")
    @NotBlank(message = "The name must not be empty")
    @Pattern(regexp = "^[\\D]*$", message = "Field must not contain digits")
    private String name;
    @NotNull(message = "The isbn must not be null")
    @NotBlank(message = "The isbn must not be empty")
    private String isbn;
    @NotNull(message = "Book type must not be empty")
    private BookType bookType;
    @Positive(message = "Price must be positive")
    @NotNull(message = "The price must not be null")
    private BigDecimal price;
}
