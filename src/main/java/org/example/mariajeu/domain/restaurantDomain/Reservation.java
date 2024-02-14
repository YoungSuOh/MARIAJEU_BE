package org.example.mariajeu.domain.restaurantDomain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", unique = true, nullable = false)
    private Long id; //예약 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private LocalDate date; // 예약 날짜

    @Column(nullable = false)
    private LocalTime time; // 예약 시간

    @Column(nullable = false)
    private Long person; // 인원

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태

//    @Enumerated(EnumType.STRING)
//    private CorkageStatus corkageStatus; // 예약에서 corkage 상태를 띄울지?

    private LocalDateTime createdTime; // 예약을 생성한 시간

    private String content; // 추가 요청 사항

    // 예약자 정보는 user_id 매핑해서

    @Builder
    public Reservation(Restaurant restaurant, LocalDate date, LocalTime time, Long person, ReservationStatus reservationStatus, LocalDateTime createdTime, String content) {
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
        this.person = person;
        this.reservationStatus = reservationStatus;
        this.createdTime = createdTime;
        this.content = content;
    }

}

