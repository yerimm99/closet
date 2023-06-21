package com.ssp.closet.controller;

import com.ssp.closet.dto.LikeMark;
import com.ssp.closet.dto.Product;
import com.ssp.closet.dto.Account;
import com.ssp.closet.repository.LikeMarkRepository;
import com.ssp.closet.repository.ProductRepository;
import com.ssp.closet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final ProductRepository productRepository;
    private final LikeMarkRepository likeMarkRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LikeController(ProductRepository productRepository, LikeMarkRepository likeMarkRepository, AccountRepository accountRepository) {
        this.productRepository = productRepository;
        this.likeMarkRepository = likeMarkRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/products/{productId}/users/{userId}")
    public ResponseEntity<String> addLikeMark(
            @PathVariable("productId") int productId,
            @PathVariable("userId") int userId,
            @RequestParam("mark") int mark) {

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Account user = accountRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        LikeMark likeMark = new LikeMark();
        likeMark.setProduct(product);
        likeMark.setUser(user);
        likeMark.setMark(mark);

        likeMarkRepository.save(likeMark);

        return ResponseEntity.status(HttpStatus.CREATED).body("Like mark added successfully");
    }
}