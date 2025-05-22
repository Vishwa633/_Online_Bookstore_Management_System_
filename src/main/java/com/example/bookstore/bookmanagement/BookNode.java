package com.example.bookstore.bookmanagement;

public class BookNode {
    private Book data;
    private BookNode next;

    public BookNode(Book data) {
        this.data = data;
        this.next = null;
    }

    public Book getData() {
        return data;
    }

    public void setData(Book data) {
        this.data = data;
    }

    public BookNode getNext() {
        return next;
    }

    public void setNext(BookNode next) {
        this.next = next;
    }
} 