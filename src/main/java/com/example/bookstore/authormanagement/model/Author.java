package com.example.bookstore.authormanagement.model;

import java.util.List;

public abstract class Author {
    protected String name;
    protected String biography;
    protected List<String> books;

    public Author() {}

    public Author(String name, String biography, List<String> books) {
        this.name = name;
        this.biography = biography;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public abstract String getAuthorType();
}