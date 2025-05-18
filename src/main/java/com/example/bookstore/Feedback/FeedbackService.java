package com.example.bookstore.Feedback;

import java.util.LinkedList;

public interface FeedbackService {
    void addFeedback(Feedback feedback);
    LinkedList<Feedback> getAllFeedback();
    Feedback getFeedbackById(int id);
    boolean updateFeedback(int id, Feedback updatedFeedback);
    boolean deleteFeedback(int id);
} 