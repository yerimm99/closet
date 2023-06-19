package com.ssp.closet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ssp.closet.dto.Review;

public class ReviewController {
	private List<Review> reviews;

    public ReviewController() {
        this.reviews = new ArrayList<>();
    }
    
	public void writeReview() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("상품 ID를 입력하세요:");
        String productId = scanner.nextLine();

        System.out.println("사용자 ID를 입력하세요:");
        String userId = scanner.nextLine();

        System.out.println("리뷰 내용을 입력하세요:");
        String content = scanner.nextLine();

        System.out.println("별점을 입력하세요 (1부터 5까지):");
        int rating = scanner.nextInt();

        Review newReview = new Review(productId, userId, content, rating);
        reviews.add(newReview);

        System.out.println("리뷰가 작성되었습니다.");
        
        scanner.close();
    }

    public List<Review> getReviews() {
        return reviews;
    }
    // 추가적인 필드 또는 메서드가 필요한 경우 이곳에 작성할 수 있습니다.
}