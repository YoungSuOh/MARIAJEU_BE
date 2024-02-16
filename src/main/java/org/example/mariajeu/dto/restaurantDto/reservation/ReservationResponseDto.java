package org.example.mariajeu.dto.restaurantDto.reservation;

import lombok.Builder;
import org.example.mariajeu.domain.restaurantDomain.Reservation;
import org.example.mariajeu.domain.restaurantDomain.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationResponseDto {
    private Restaurant restaurant;
    private LocalDate date;
    private LocalTime time;
    private Long person;
    // User 관련 추가

    @Builder
    public ReservationResponseDto(Reservation entity) {
        this.restaurant = entity.getRestaurant();
        this.date = date;
        this.time = entity.getTime();
        this.person = entity.getPerson();
    }

    // 엔티티에서 DTO로 변환하는 메서드
//    public static ReservationResponseDto fromEntity(Reservation reservation) {
//        return ReservationResponseDto.builder()
//                .restaurant(reservation.getRestaurant())
//                .date(reservation.getDate())
//                .time(reservation.getTime())
//                .person(reservation.getPerson())
//                .build();
//    }
}
