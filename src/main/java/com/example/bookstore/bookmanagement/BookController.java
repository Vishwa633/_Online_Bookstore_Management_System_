package com.example.bookstore.bookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String sortBy) {
        List<Book> books = bookService.getAllBooksSortedByTitle();
        if ("author".equals(sortBy)) {
            // Implement and call quickSortByAuthor
        } else if ("price".equals(sortBy)) {
            // Implement and call quickSortByPrice
        }
        return books;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.removeBook(id);
    }
}
