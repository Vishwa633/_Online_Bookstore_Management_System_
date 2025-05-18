package com.example.bookstore.ReviewManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewManager reviewManager;

    public ReviewController() {
        this.reviewManager = new ReviewManager();
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        try {
            reviewManager.addReview(review);
            return ResponseEntity.ok(review);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewManager.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable int bookId) {
        List<Review> reviews = reviewManager.getReviewsForBook(bookId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable int bookId) {
        double average = reviewManager.getAverageRatingForBook(bookId);
        return ResponseEntity.ok(average);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
       
        return ResponseEntity.ok().build();
    }
} 
