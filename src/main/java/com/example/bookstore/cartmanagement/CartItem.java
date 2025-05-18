package com.example.bookstore.cartmanagement;

/**
 * Interface defining the contract for cart items
 */
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