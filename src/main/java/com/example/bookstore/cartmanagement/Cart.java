package com.example.bookstore.cartmanagement;

import java.util.LinkedList;
import java.util.List;

/**
 * Cart class using LinkedList to store cart items
 * Demonstrates LinkedList usage and encapsulation
 */
public class Cart {
    private List<CartItem> items;
    private String userId;

    public Cart(String userId) {
        this.userId = userId;
        this.items = new LinkedList<>();
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
        return items.removeIf(item -> item.getId() == itemId);
    }

    // Update item quantity
    public boolean updateQuantity(int itemId, int quantity) {
        CartItem item = findItemById(itemId);
        if (item != null) {
            item.setQuantity(quantity);
            return true;
        }
        return false;
    }

    // Find item by ID
    public CartItem findItemById(int itemId) {
        return items.stream()
                .filter(item -> item.getId() == itemId)
                .findFirst()
                .orElse(null);
    }

    // Get all items
    public List<CartItem> getItems() {
        return new LinkedList<>(items); // Return a copy for encapsulation
    }

    // Get total number of items
    public int getTotalItems() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    // Calculate total price
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
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
                ", items=" + items +
                ", totalItems=" + getTotalItems() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
} 