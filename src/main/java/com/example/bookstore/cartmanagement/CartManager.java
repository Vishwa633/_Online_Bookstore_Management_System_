package com.example.bookstore.cartmanagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * CartManager class to handle cart operations
 * Demonstrates polymorphism and encapsulation
 */
@Component
public class CartManager {
    private Map<String, Cart> userCarts = new HashMap<>();

    public CartManager() {
        this.userCarts = new HashMap<>();
    }

    // Get or create cart for user
    public Cart getCart(String userId) {
        return userCarts.computeIfAbsent(userId, Cart::new);
    }

    // Add item to user's cart
    public void addToCart(String userId, CartItem item) {
        Cart cart = getCart(userId);
        cart.addItem(item);
    }

    // Remove item from user's cart
    public boolean removeFromCart(String userId, int itemId) {
        Cart cart = getCart(userId);
        return cart.removeItem(itemId);
    }

    // Update item quantity in user's cart
    public boolean updateCartItemQuantity(String userId, int itemId, int quantity) {
        Cart cart = getCart(userId);
        return cart.updateQuantity(itemId, quantity);
    }

    // Get cart total for user
    public double getCartTotal(String userId) {
        Cart cart = getCart(userId);
        return cart.getTotalPrice();
    }

    // Get number of items in user's cart
    public int getCartItemCount(String userId) {
        Cart cart = getCart(userId);
        return cart.getTotalItems();
    }

    // Clear user's cart
    public void clearCart(String userId) {
        Cart cart = getCart(userId);
        cart.clear();
    }

    // Check if user's cart is empty
    public boolean isCartEmpty(String userId) {
        Cart cart = getCart(userId);
        return cart.isEmpty();
    }

    // Get all items in user's cart
    public java.util.List<CartItem> getCartItems(String userId) {
        Cart cart = getCart(userId);
        return cart.getItems();
    }

    // Remove user's cart (e.g., after checkout)
    public void removeCart(String userId) {
        userCarts.remove(userId);
    }

} 