package com.project.usedtextbooks.service;

import com.project.usedtextbooks.domain.Book;
import com.project.usedtextbooks.dto.BookInventoryDTO;
import com.project.usedtextbooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    // The repository layer for accessing book data
    private final BookRepository bookRepository;

    // Constructor to inject the BookRepository dependency
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Retrieves a list of all books from the database
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Finds a book by its ID, if present
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Saves a new or existing book to the database
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Deletes a book by its ID from the database
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Updates an existing book's details in the database
    public Book updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            throw new IllegalArgumentException("No book with ID " + id + " exists.");
        }
    }

    // Processes the purchase of a book by marking it as not in stock and
    // incrementing purchase count
    public String buyBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "Book not found";
        }
        if (!book.isInStock()) {
            return "Book is out of stock";
        }

        book.setInStock(false);
        book.setPurchaseCount(book.getPurchaseCount() + 1);
        bookRepository.save(book);
        return "Book purchased successfully";
    }

    // Processes the sale of a used book, marks it as in stock, and applies a
    // discount to the price
    public String sellBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "Book not found";
        }

        if (book.isInStock()) {
            return "Cannot sell the book. It's already in the inventory.";
        }

        // Apply a 10% discount to the book's price
        book.setPrice(book.getPrice() * 0.9);
        // Mark the book as in stock after selling
        book.setInStock(true);
        bookRepository.save(book);
        return String.format("Book sold successfully with price: %.2f", book.getPrice());
    }

    // Adds a new book to the inventory with its initial price and marks it as in
    // stock
    public String sellNewBook(Book book) {
        book.setInStock(true);
        bookRepository.save(book);
        return String.format("New book added successfully with price: %.2f", book.getPrice());
    }

    // Compiles an inventory of books, grouped by ISBN, with a total count of books
    // in stock
    public List<BookInventoryDTO> getInventoryOfBooks() {
        List<Book> allBooks = bookRepository.findAll();

        // A map to track inventory by ISBN
        Map<String, BookInventoryDTO> bookMap = new HashMap<>();

        allBooks.forEach(book -> {
            String isbn = book.getIsbn();
            if (bookMap.containsKey(isbn)) {
                // If the book ISBN exists in the map, update the inventory count
                BookInventoryDTO existingBook = bookMap.get(isbn);
                if (book.isInStock()) {
                    existingBook.setTotalInventory(existingBook.getTotalInventory() + 1);
                }
            } else {
                // If the book ISBN does not exist, create a new DTO and add it to the map
                BookInventoryDTO dto = new BookInventoryDTO();
                dto.setIsbn(book.getIsbn());
                dto.setAuthors(book.getAuthors());
                dto.setTitle(book.getTitle());
                dto.setEdition(book.getEdition());
                // Set inventory to 1 if in stock, otherwise 0
                dto.setTotalInventory(book.isInStock() ? 1 : 0);
                bookMap.put(isbn, dto);
            }
        });

        // Return a list of BookInventoryDTOs representing the book inventory
        return new ArrayList<>(bookMap.values());
    }
}
