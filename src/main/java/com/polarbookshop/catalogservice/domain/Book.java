package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// The domain model is implemented as a record, an immutable object.
public record Book(

    @NotBlank(message = "The book ISBN must be defined.")
    // The annotated element must match the specified regular expression (standard ISBN format).
    @Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})$",
        message = "The ISBN format must be valid."
    )
    // Uniquely identifies a book
    String isbn,

    // The annotated element must not be null and must contain at least one non-whitespace character.
    @NotBlank(message = "The book title must be defined.")
    String title,

    @NotBlank(message = "The book author must be defined.")
    String author,

    @NotNull(message = "The book price must be defined.")
    // The annotated element must not be null and must be greater than zero.
    @Positive(message = "The book price must be greater than zero.")
    Double price
) {}
