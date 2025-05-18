package com.example.bookstore.Feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackApiController {
    private static final List<Feedback> feedbacks = new ArrayList<>();
    private static int nextId = 1;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addFeedback(@RequestBody Feedback feedback) {
        try {
            if (feedback.getName() == null || feedback.getEmail() == null || feedback.getMessage() == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", "Missing required fields"));
            }

            feedback.setId(nextId++);
            feedback.setDate(new Date().toString());
            feedbacks.add(feedback);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Feedback submitted successfully",
                "feedback", feedback
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("success", false, "message", "Error submitting feedback: " + e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        // Sort feedbacks by date in descending order (newest first)
        List<Feedback> sortedFeedbacks = new ArrayList<>(feedbacks);
        sortedFeedbacks.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        return ResponseEntity.ok(sortedFeedbacks);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Feedback>> getUserFeedback(@PathVariable String email) {
        List<Feedback> userFeedbacks = feedbacks.stream()
            .filter(f -> f.getEmail().equals(email))
            .toList();
        return ResponseEntity.ok(userFeedbacks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFeedback(@PathVariable int id, @RequestParam String email) {
        try {
            Optional<Feedback> feedbackToDelete = feedbacks.stream()
                .filter(f -> f.getId() == id && f.getEmail().equals(email))
                .findFirst();

            if (feedbackToDelete.isPresent()) {
                feedbacks.remove(feedbackToDelete.get());
                return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Feedback deleted successfully"
                ));
            } else {
                return ResponseEntity.badRequest()
                    .body(Map.of(
                        "success", false,
                        "message", "Feedback not found or you don't have permission to delete it"
                    ));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of(
                    "success", false,
                    "message", "Error deleting feedback: " + e.getMessage()
                ));
        }
    }
} 