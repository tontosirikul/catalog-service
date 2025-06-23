package com.polarbookshop.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.polarbookshop.catalogservice.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

// Loads a full Spring web application context and a Servlet container listening on a random port
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient; // Utility to perform REST calls for testing

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
		webTestClient
			.post() // Sends an HTTP POST request
      		.uri("/books") // Sends the request to the "/books" endpoint
      		.bodyValue(expectedBook) // Adds the book in the request body
      		.exchange() // Sends the request
      		.expectStatus().isCreated() // Verifies that the HTTP response has status “201 Created”
      		.expectBody(Book.class).value(createdBook -> {
        		assertThat(createdBook).isNotNull(); // Verifies that the HTTP response has a non-null body
        		assertThat(createdBook.isbn())
					.isEqualTo(expectedBook.isbn()); // Verifies that the created object is as expected
      		});
  	}


}
