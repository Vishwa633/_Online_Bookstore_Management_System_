package com.example.bookstore.cartmanagement;

import java.util.ArrayList;
import java.util.List;

public class CustomCartList {
    private CartItemNode head;
    private int size;

    public CustomCartList() {
        this.head = null;
        this.size = 0;
    }

    public void add(CartItem item) {
        CartItemNode newNode = new CartItemNode(item);
        if (head == null) {
            head = newNode;
        } else {
            CartItemNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public CartItem findById(int id) {
        CartItemNode current = head;
        while (current != null) {
            if (current.getData().getId() == id) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean remove(int id) {
        if (head == null) {
            return false;
        }

        if (head.getData().getId() == id) {
            head = head.getNext();
            size--;
            return true;
        }

        CartItemNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getId() == id) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean updateQuantity(int id, int quantity) {
        CartItem item = findById(id);
        if (item != null) {
            item.setQuantity(quantity);
            return true;
        }
        return false;
    }

    public List<CartItem> getAllItems() {
        List<CartItem> items = new ArrayList<>();
        CartItemNode current = head;
        while (current != null) {
            items.add(current.getData());
            current = current.getNext();
        }
        return items;
    }

    public int getTotalItems() {
        int total = 0;
        CartItemNode current = head;
        while (current != null) {
            total += current.getData().getQuantity();
            current = current.getNext();
        }
        return total;
    }

    public double getTotalPrice() {
        double total = 0;
        CartItemNode current = head;
        while (current != null) {
            total += current.getData().getSubtotal();
            current = current.getNext();
        }
        return total;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
} 