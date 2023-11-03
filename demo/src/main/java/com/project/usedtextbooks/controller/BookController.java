package com.project.usedtextbooks.controller;

import com.project.usedtextbooks.domain.Book;
import com.project.usedtextbooks.dto.BookInventoryDTO;
import com.project.usedtextbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    // Service layer that handles business logic for books
    private final BookService bookService;

    // Autowired to inject BookService dependency
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint to retrieve a list of all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint to retrieve a single book by its ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElse(null);
    }

    // Endpoint to create a new book record
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // Endpoint to delete a book by its ID
    @PostMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Endpoint to update a book's information by its ID
    @PostMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    // Endpoint to handle the purchase of a book by its ID
    @PostMapping("/buy")
    public String buyBook(@RequestBody Book book) {
        if (book.getId() == null) {
            return "Book ID is required";
        }
        return bookService.buyBook(book.getId());
    }

    // Endpoint to handle the selling of a book. If an ID is provided, it sells an
    // existing book; otherwise, it adds a new book to the stock.
    @PostMapping("/sell")
    public String sellBook(@RequestBody Book book) {
        if (book.getId() != null) {
            return bookService.sellBook(book.getId());
        } else {
            return bookService.sellNewBook(book);
        }
    }

    // Endpoint to retrieve a summary of books in inventory, grouped by ISBN
    @GetMapping("/inventory")
    public List<BookInventoryDTO> getInventoryOfBooks() {
        return bookService.getInventoryOfBooks();
    }
    // ... other methods ...
}
