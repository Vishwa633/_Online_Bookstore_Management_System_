package com.example.bookstore.cartmanagement;

public class BookCartItem implements CartItem {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
    private String imageUrl;

    public BookCartItem(int id, String title, String author, double price, int quantity, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "BookCartItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
} 
