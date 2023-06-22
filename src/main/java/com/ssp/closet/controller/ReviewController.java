package com.ssp.closet.controller;

import com.ssp.closet.dto.Review;
import com.ssp.closet.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/closet/mypage.do")
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

    @GetMapping("/closet/mypage.do")
    public String getReviewListToMe(Model model) {
        // Add logic to retrieve the review list to display in the JSP
        // For example:
        List<Review> reviewList = reviewRepository.findAll();
        model.addAttribute("reviewList", reviewList);
        return "reviews/listToMe";
    }

    @GetMapping("/closet/mypage.do")
    public String getReviewRegisterForm(Model model) {
        // Add logic to populate any necessary data in the model for the register form
        return "review/registerForm";
    }

    // Add other necessary endpoints and methods as per your requirements
}

