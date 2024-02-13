package org.example.mariajeu.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long id; // 리뷰 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String content; // 리뷰 내용

    //    private String image; // 리뷰 이미지 -> S3 사용해서 URL로?

    @Column(nullable = false)
    private LocalDate date; // 리뷰 생성 날짜

    @Column(nullable = false)
    private LocalTime time; // 리뷰 생성 시간

}
