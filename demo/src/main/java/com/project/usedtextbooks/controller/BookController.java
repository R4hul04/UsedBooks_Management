package com.project.usedtextbooks.controller;

import com.project.usedtextbooks.domain.Book;
import com.project.usedtextbooks.dto.BookInventoryDTO;
import com.project.usedtextbooks.dto.Response;
import com.project.usedtextbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElse(null);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @PostMapping("/buy/{id}")
    public Response buyBook(@PathVariable Long id) {
        return bookService.buyBook(id);
    }

    @PostMapping("/sell")
    public Response sellBook(@RequestBody Book book) {
        if (book.getId() != null) {
            return bookService.sellBook(book.getId());
        } else {
            return bookService.sellNewBook(book);
        }
    }

    @GetMapping("/inventory")
    public List<BookInventoryDTO> getInventoryOfBooks() {
        return bookService.getInventoryOfBooks();
    }
    // ... other methods ...
}
