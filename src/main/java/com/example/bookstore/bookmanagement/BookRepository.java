package com.example.bookstore.bookmanagement;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    private final CustomLinkedList books;

    public BookRepository() {
        books = new CustomLinkedList();
        // Initialize with some sample books
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 19.99, "image/book1.jpg"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", 15.99, "image/book2.jpg"));
        books.add(new Book(3, "1984", "George Orwell", 14.99, "image/book3.jpg"));
        books.add(new Book(4, "Pride and Prejudice", "Jane Austen", 12.99, "image/book4.jpg"));
        books.add(new Book(5, "The Catcher in the Rye", "J.D. Salinger", 13.99, "image/book5.jpg"));
        books.add(new Book(6, "The Hobbit", "J.R.R. Tolkien", 16.99, "image/book6.jpg"));
        books.add(new Book(7, "The Lord of the Rings", "J.R.R. Tolkien", 24.99, "image/book7.jpg"));
        books.add(new Book(8, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 17.99, "image/book8.jpg"));
        books.add(new Book(9, "The Alchemist", "Paulo Coelho", 11.99, "image/book9.jpg"));
        books.add(new Book(10, "The Little Prince", "Antoine de Saint-Exupéry", 9.99, "image/book10.jpg"));
        books.add(new Book(11, "The Da Vinci Code", "Dan Brown", 15.99, "image/book11.jpg"));
        books.add(new Book(12, "The Kite Runner", "Khaled Hosseini", 14.99, "image/book12.jpg"));
        books.add(new Book(13, "The Hunger Games", "Suzanne Collins", 13.99, "image/book13.jpg"));
        books.add(new Book(14, "The Fault in Our Stars", "John Green", 12.99, "image/book14.jpg"));
        books.add(new Book(15, "The Book Thief", "Markus Zusak", 14.99, "image/book15.jpg"));
        books.add(new Book(16, "The Giver", "Lois Lowry", 11.99, "image/book16.jpg"));
        books.add(new Book(17, "The Chronicles of Narnia", "C.S. Lewis", 19.99, "image/book17.jpg"));
        books.add(new Book(18, "The Secret Garden", "Frances Hodgson Burnett", 9.99, "image/book18.jpg"));
        books.add(new Book(19, "The Picture of Dorian Gray", "Oscar Wilde", 10.99, "image/book19.jpg"));
        books.add(new Book(20, "The Adventures of Sherlock Holmes", "Arthur Conan Doyle", 12.99, "image/book20.jpg"));
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books.getAllBooks();
    }

    public Book getBookById(int id) {
        return books.getById(id);
    }

    public void removeBook(int id) {
        books.remove(id);
    }

    public Book updateBook(Book updatedBook) {
        return books.update(updatedBook);
    }
} 
