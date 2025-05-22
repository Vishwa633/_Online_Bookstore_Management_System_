package com.example.bookstore.cartmanagement;

import java.util.List;

public class Cart {
    private CustomCartList items;
    private String userId;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new CustomCartList();
    }

    // Add item to cart
    public void addItem(CartItem item) {
        // Check if item already exists
        CartItem existingItem = findItemById(item.getId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    // Remove item from cart
    public boolean removeItem(int itemId) {
        return items.remove(itemId);
    }

    // Update item quantity
    public boolean updateQuantity(int itemId, int quantity) {
        return items.updateQuantity(itemId, quantity);
    }

    // Find item by ID
    public CartItem findItemById(int itemId) {
        return items.findById(itemId);
    }

    // Get all items
    public List<CartItem> getItems() {
        return items.getAllItems();
    }

    // Get total number of items
    public int getTotalItems() {
        return items.getTotalItems();
    }

    // Calculate total price
    public double getTotalPrice() {
        return items.getTotalPrice();
    }

    // Clear cart
    public void clear() {
        items.clear();
    }

    // Get user ID
    public String getUserId() {
        return userId;
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId='" + userId + '\'' +
                ", items=" + items.getAllItems() +
                ", totalItems=" + getTotalItems() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
} 
