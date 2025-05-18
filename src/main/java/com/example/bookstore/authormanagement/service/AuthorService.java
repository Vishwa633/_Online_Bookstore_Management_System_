package com.example.bookstore.authormanagement.service;

import com.example.bookstore.authormanagement.model.Author;
import com.example.bookstore.authormanagement.repository.AuthorFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorFileRepository repository = new AuthorFileRepository();

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author getAuthorByName(String name) {
        return repository.findAll().stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Author> searchByBookTitle(String title) {
        return repository.findAll().stream()
                .filter(author -> author.getBooks().stream()
                        .anyMatch(book -> book.toLowerCase().contains(title.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void addAuthor(Author author) {
        repository.save(author);
    }

    public void updateAuthor(Author author) {
        repository.update(author);
    }

    public void deleteAuthor(String name) {
        repository.delete(name);
    }
}
