package org.example.mariajeu.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.Menu;
import org.example.mariajeu.domain.Reservation;
import org.example.mariajeu.domain.Restaurant;
import org.example.mariajeu.domain.Review;
import org.example.mariajeu.dto.menu.MenuDto;

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
