package com.example.bookstore.authormanagement.model;

import java.time.LocalDate;
import java.util.List;

public class PermanentAuthor extends Author {
    private LocalDate hireDate;

    public PermanentAuthor(String name, String biography, List<String> books, LocalDate hireDate) {
        super(name, biography, books);
        this.hireDate = hireDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String getAuthorType() {
        return "PermanentAuthor";
    }
}
