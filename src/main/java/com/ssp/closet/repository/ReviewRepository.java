package com.ssp.closet.repository;

import com.ssp.closet.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Add additional methods if needed
}
