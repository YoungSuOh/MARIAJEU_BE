package org.example.mariajeu.dto.restaurantDto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Review;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private String content;
    private String reviewImg;

    @Builder
    public ReviewDto(String content, String reviewImg, LocalDate createdDate, LocalTime createdTime) {
        this.content = content;
        this.reviewImg = reviewImg;
    }

    // entity -> dto
    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .content(review.getContent())
                .reviewImg(review.getReviewImg())
                .build();
    }


}
