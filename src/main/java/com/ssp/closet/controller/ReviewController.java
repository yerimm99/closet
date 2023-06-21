package com.ssp.closet.controller;

import com.ssp.closet.dto.Review;
import com.ssp.closet.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {
        // Add any necessary logic to populate the model
        return "review/index";
    }

    @GetMapping("/menu")
    public String getMenuPage(Model model) {
        // Add any necessary logic to populate the model
        return "review/menu";
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping("/{orderId}")
    public Review getReviewByOrderId(@PathVariable int orderId) {
        return reviewRepository.findById(orderId).orElse(null);
    }

    // Add other necessary endpoints and methods as per your requirements
}

