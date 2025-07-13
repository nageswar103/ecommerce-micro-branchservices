package com.example.reviewservice.service;

import com.example.reviewservice.entity.Review;
import com.example.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existing -> {
            existing.setProductId(updatedReview.getProductId());
            existing.setUserId(updatedReview.getUserId());
            existing.setRating(updatedReview.getRating());
            existing.setComment(updatedReview.getComment());
            existing.setCreatedAt(LocalDateTime.now());
            return reviewRepository.save(existing);
        }).orElse(null);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
