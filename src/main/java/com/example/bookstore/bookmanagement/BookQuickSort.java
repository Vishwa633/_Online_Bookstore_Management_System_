package com.example.bookstore.bookmanagement;

import java.util.List;

public class BookQuickSort {
    // Sort by title
    public static void quickSortByTitle(List<Book> books, int low, int high) {
        if (low < high) {
            int pi = partition(books, low, high);
            quickSortByTitle(books, low, pi - 1);
            quickSortByTitle(books, pi + 1, high);
        }
    }

    private static int partition(List<Book> books, int low, int high) {
        String pivot = books.get(high).getTitle();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (books.get(j).getTitle().compareToIgnoreCase(pivot) < 0) {
                i++;
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }
        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);
        return i + 1;
    }
} 