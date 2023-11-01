package com.project.usedtextbooks.service;

import com.project.usedtextbooks.domain.Book;
import com.project.usedtextbooks.dto.BookInventoryDTO;
import com.project.usedtextbooks.dto.Response;
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

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            throw new IllegalArgumentException("No book with ID " + id + " exists.");
        }
    }

    public Response buyBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return new Response(false, "Book not found");
        }
        if (!book.isInStock()) { // Updated line
            return new Response(false, "Book is out of stock");
        }

        book.setInStock(false); // Updated line
        bookRepository.save(book);
        return new Response(true, "Book purchased successfully");
    }

    public Response sellBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return new Response(false, "Book not found");
        }

        if (book.isInStock()) { // Updated line
            return new Response(false, "Cannot sell the book. It's already in the inventory.");
        }

        book.setPrice(book.getPrice() * 0.9);
        book.setInStock(true); // Updated line
        bookRepository.save(book);
        return new Response(true, "Book sold successfully", book.getPrice());
    }

    public Response sellNewBook(Book book) {
        book.setInStock(true); // Set the book as in stock
        bookRepository.save(book);
        return new Response(true, "New book added successfully with price: " + book.getPrice());
    }

    public List<BookInventoryDTO> getInventoryOfBooks() {
        List<Book> allBooks = bookRepository.findAll();

        Map<String, BookInventoryDTO> bookMap = new HashMap<>();

        allBooks.forEach(book -> {
            String isbn = book.getIsbn();
            if (bookMap.containsKey(isbn)) {
                BookInventoryDTO existingBook = bookMap.get(isbn);
                if (book.isInStock()) { // Updated line
                    existingBook.setTotalInventory(existingBook.getTotalInventory() + 1);
                }
            } else {
                BookInventoryDTO dto = new BookInventoryDTO();
                dto.setIsbn(book.getIsbn());
                dto.setAuthors(book.getAuthors());
                dto.setTitle(book.getTitle());
                dto.setEdition(book.getEdition());
                dto.setTotalInventory(book.isInStock() ? 1 : 0); // Updated line
                bookMap.put(isbn, dto);
            }
        });

        return new ArrayList<>(bookMap.values());
    }
}
