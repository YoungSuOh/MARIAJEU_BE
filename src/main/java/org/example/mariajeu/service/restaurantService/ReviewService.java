package org.example.mariajeu.service.restaurantService;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Restaurant;
import org.example.mariajeu.domain.restaurantDomain.Review;
import org.example.mariajeu.dto.restaurantDto.review.ReviewDto;
import org.example.mariajeu.repository.restaurantRepository.RestaurantRepository;
import org.example.mariajeu.repository.restaurantRepository.ReviewRepository;
import org.example.mariajeu.service.S3Upload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final S3Upload s3Upload;


    // 레스토랑 메뉴 추가 (사장)
    @Transactional
    public Long registerReview(ReviewDto reviewDto, Long restaurantId, MultipartFile reviewImg) {
        String postImg = null;
        if (!reviewImg.isEmpty()) {
            try {
                postImg = s3Upload.uploadFiles(reviewImg, "menuImg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("레스토랑을 찾을 수 없습니다. ID: " + restaurantId));

        Review review = new Review(reviewDto, restaurant, postImg);

        return reviewRepository.save(review).getId();
    }

    // 레스토랑 메뉴 삭제 (사장)
    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}
