package com.polarbookshop.catalogservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;

import jakarta.validation.Valid;

@RestController // Stereotype annotation marking a class as a Spring component and a source of handlers for REST endpoints
@RequestMapping("books") // Identifies the root path mapping URI for which the class provides handlers ("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Maps HTTP GET requests to the specific handler method
    @GetMapping // 
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    // A URI template variable appended to the root path mapping URI ("/books/{isbn}")
    // @PathVariable binds a method parameter to a URI template variable ({isbn}).
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    // Maps HTTP POST requests to the specific handler method
    // Returns a 201 status if the book is created successfully
    // Returns a 201 status if the book is created successfully
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    // Maps HTTP DELETE requests to the specific handler method
    // Returns a 204 status if the book is deleted successfully
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    // Maps HTTP PUT requests to the specific handler method
    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}
