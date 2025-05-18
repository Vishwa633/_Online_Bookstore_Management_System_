package com.example.bookstore.bookmanagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        return repository.addBook(book);
    }

    public Book updateBook(Book book) {
        return repository.updateBook(book);
    }

    public List<Book> getAllBooksSortedByTitle() {
        List<Book> books = repository.getAllBooks();
        BookQuickSort.quickSortByTitle(books, 0, books.size() - 1);
        return books;
    }

    public List<Book> getAllBooksSortedByPrice() {
        List<Book> books = repository.getAllBooks();
        BookQuickSort.quickSortByPrice(books, 0, books.size() - 1);
        return books;
    }

    public void removeBook(int id) {
        repository.removeBook(id);
    }
} 
