package com.polarbookshop.catalogservice.domain;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {
    public static Validator validator;

    @BeforeAll // Identifies a block of code executed before all tests in the class
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test // Identifies a test case
    void whenAllFieldsCorrectThenValidatationSucceeds() {
        // Creates a book with a valid ISBN
        var book = new Book("1234567890", "Title", "Author", 9.90); 
        Set<ConstraintViolation<Book>> violations = validator.validate(book); 
        // Asserts that there is no validation error
        assertThat(violations).isEmpty(); 
    }
    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        // Creates a book with a non-valid ISBN code
        var book = new Book("a234567890", "Title", "Author", 9.90); 
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        // Asserts that the violated validation constraint is about the incorrect ISBN
        assertThat(violations.iterator().next().getMessage())
            .isEqualTo("The ISBN format must be valid.");
    }
}
