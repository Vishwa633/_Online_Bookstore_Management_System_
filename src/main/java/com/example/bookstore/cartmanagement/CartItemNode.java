package com.example.bookstore.cartmanagement;

public class CartItemNode {
    private CartItem data;
    private CartItemNode next;

    public CartItemNode(CartItem data) {
        this.data = data;
        this.next = null;
    }

    public CartItem getData() {
        return data;
    }

    public void setData(CartItem data) {
        this.data = data;
    }

    public CartItemNode getNext() {
        return next;
    }

    public void setNext(CartItemNode next) {
        this.next = next;
    }
} 