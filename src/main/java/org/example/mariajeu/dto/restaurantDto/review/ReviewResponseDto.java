package org.example.mariajeu.dto.restaurantDto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Review;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private String content;
    private String reviewImg;
    private LocalDateTime createdDateTime;

    @Builder
    public ReviewResponseDto(String content, String reviewImg, LocalDateTime createdDateTime) {
        this.content = content;
        this.reviewImg = reviewImg;
        this.createdDateTime = createdDateTime;
    }

    // entity -> dto
    public static ReviewResponseDto fromEntity(Review review) {
        return ReviewResponseDto.builder()
                .content(review.getContent())
                .reviewImg(review.getReviewImg())
                .createdDateTime(review.getCreatedDateTime())
                .build();
    }


}
