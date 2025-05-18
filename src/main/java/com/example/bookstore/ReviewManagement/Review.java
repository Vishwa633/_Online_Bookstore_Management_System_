package com.example.bookstore.ReviewManagement;

public class Review {
    private String reviewerName;
    private int rating;
    private String comment;
    private int bookId;

    // Constructor
    public Review(String reviewerName, int rating, String comment, int bookId) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
        this.bookId = bookId;
    }

    // Getters and Setters (Encapsulation)
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewerName='" + reviewerName + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", bookId=" + bookId +
                '}';
    }
} 