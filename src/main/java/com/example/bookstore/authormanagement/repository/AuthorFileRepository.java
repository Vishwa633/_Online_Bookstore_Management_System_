package com.example.bookstore.authormanagement.repository;

import com.example.bookstore.authormanagement.model.Author;
import com.example.bookstore.authormanagement.model.GuestAuthor;
import com.example.bookstore.authormanagement.model.PermanentAuthor;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorFileRepository {
    private final String filePath = "authors.txt";

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Author author = parseAuthor(line);
                if (author != null) {
                    authors.add(author);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void save(Author author) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(formatAuthor(author));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Author updatedAuthor) {
        List<Author> authors = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Author author : authors) {
                if (author.getName().equals(updatedAuthor.getName())) {
                    writer.write(formatAuthor(updatedAuthor));
                } else {
                    writer.write(formatAuthor(author));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        List<Author> authors = findAll().stream()
                .filter(a -> !a.getName().equals(name))
                .collect(Collectors.toList());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Author author : authors) {
                writer.write(formatAuthor(author));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Author parseAuthor(String line) {
        try {
            String[] parts = line.split("\\|");
            String name = parts[0];
            String bio = parts[1];
            List<String> books = Arrays.asList(parts[2].split(","));
            String type = parts[3];
            if (type.equals("GuestAuthor")) {
                return new GuestAuthor(name, bio, books, parts[4]);
            } else if (type.equals("PermanentAuthor")) {
                return new PermanentAuthor(name, bio, books, LocalDate.parse(parts[4]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String formatAuthor(Author author) {
        String base = String.join("|",
                author.getName(),
                author.getBiography(),
                String.join(",", author.getBooks()),
                author.getAuthorType()
        );
        if (author instanceof GuestAuthor) {
            return base + "|" + ((GuestAuthor) author).getAffiliation();
        } else if (author instanceof PermanentAuthor) {
            return base + "|" + ((PermanentAuthor) author).getHireDate().toString();
        }
        return base;
    }
}
