package com.project.usedtextbooks;

import com.project.usedtextbooks.domain.Book;
import com.project.usedtextbooks.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(BookRepository bookRepository) {
		return args -> {
			// Example: Initialize 5 copies of the same book with the same ISBN but
			// different IDs.
			for (int i = 0; i < 5; i++) {
				Book book1 = new Book();
				book1.setIsbn("9781234567890");
				book1.setAuthors("Cute Dog");
				book1.setTitle("A DOG'S LIFE");
				book1.setEdition("1st Edition");
				book1.setPrice(100.0);
				book1.setInventory(1);
				bookRepository.save(book1);
			}

			// Continue adding other books...
		};
	}
}
