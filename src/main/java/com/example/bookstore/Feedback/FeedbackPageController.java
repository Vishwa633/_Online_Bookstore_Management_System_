package com.example.bookstore.Feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackPageController {
    @GetMapping("/feedback")
    public String feedbackPage() {
        return "feedback";
    }
} 