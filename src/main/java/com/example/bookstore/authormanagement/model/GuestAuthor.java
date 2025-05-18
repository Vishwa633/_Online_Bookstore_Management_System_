package com.example.bookstore.authormanagement.model;

import java.util.List;

public class GuestAuthor extends Author {
    private String affiliation;

    public GuestAuthor(String name, String biography, List<String> books, String affiliation) {
        super(name, biography, books);
        this.affiliation = affiliation;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    @Override
    public String getAuthorType() {
        return "GuestAuthor";
    }
}
