package com.example.bookstore.cartmanagement;

public interface CartItem {
    int getId();
    String getTitle();
    String getAuthor();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    double getSubtotal();
    String getImageUrl();
} 
