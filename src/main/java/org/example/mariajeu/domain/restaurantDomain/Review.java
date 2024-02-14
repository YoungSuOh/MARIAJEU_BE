package org.example.mariajeu.domain.restaurantDomain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.dto.restaurantDto.review.ReviewDto;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long id; // 리뷰 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private String content; // 리뷰 내용

    private String reviewImg; // 리뷰 이미지 -> S3 사용해서 URL로?

    @Builder
    public Review(ReviewDto reviewDto, Restaurant restaurant, String reviewImg) {
        this.restaurant = restaurant;
        this.content = reviewDto.getContent();
        this.reviewImg = reviewImg;
    }


}
