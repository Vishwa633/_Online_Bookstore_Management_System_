package com.example.bookstore.ReviewManagement;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewManager {
    // Using static list to persist reviews across instances
    private static final LinkedList<Review> reviews = new LinkedList<>();

    public ReviewManager() {
        // Constructor empty as we're using static list
    }

    // Add a review
    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        reviews.addFirst(review); // Add new reviews at the start of the list
    }

    // Remove a review
    public boolean removeReview(Review review) {
        return reviews.remove(review);
    }

    // Get all reviews
    public List<Review> getAllReviews() {
        return new LinkedList<>(reviews); // Return a copy to prevent external modifications
    }

    // Get reviews for a specific book
    public List<Review> getReviewsForBook(int bookId) {
        return reviews.stream()
                .filter(review -> review.getBookId() == bookId)
                .collect(Collectors.toList());
    }

    // Get average rating for a book
    public double getAverageRatingForBook(int bookId) {
        return reviews.stream()
                .filter(review -> review.getBookId() == bookId)
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
} 