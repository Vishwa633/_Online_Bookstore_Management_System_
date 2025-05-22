package com.example.bookstore.bookmanagement;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList {
    private BookNode head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(Book book) {
        BookNode newNode = new BookNode(book);
        if (head == null) {
            head = newNode;
        } else {
            BookNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    

    public Book getById(int id) {
        BookNode current = head;
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

        BookNode current = head;
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

    public Book update(Book updatedBook) {
        BookNode current = head;
        while (current != null) {
            if (current.getData().getId() == updatedBook.getId()) {
                current.setData(updatedBook);
                return updatedBook;
            }
            current = current.getNext();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        BookNode current = head;
        while (current != null) {
            books.add(current.getData());
            current = current.getNext();
        }
        return books;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
} 
