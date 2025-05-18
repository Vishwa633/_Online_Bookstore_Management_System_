package com.example.bookstore.bookmanagement;

public class Book {
    private int id
    private String title;
    private String author;
    private double price;
    private String image;

    public Book() {}

     public Book(String title, String author, double price, String image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.image = image;
    }
    

    public Book(int id, String title, String author, double price, String image) {
        this(id, title, author, price);
        this.image = image;
    }

    // Encapsulation: Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    // Polymorphism: Can be overridden
    public String getType() {
        return "Book";
    }

    @Override
    public String toString() {
        return "[" + getType() + "] " + title + " by " + author + " ($" + price + ")";
    }
} 
