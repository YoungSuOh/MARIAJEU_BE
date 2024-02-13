package org.example.mariajeu.dto.reservation;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.Reservation;
import org.example.mariajeu.domain.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
//@Data // -> 무한 참조 주의
public class ReservationRequestDto {
    private Long restaurantId; // 레스토랑 ID만을 참조하도록 수정
    private LocalDate date;
    private LocalTime time;
    private Long person;
    // User 관련 추가

    @Builder
    public ReservationRequestDto(Long restaurantId, LocalDate date, LocalTime time, Long person) {
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
        this.person = person;
    }

    // dto -> entity
    public Reservation toEntity(Restaurant restaurant) {
        return Reservation.builder()
                .restaurant(restaurant) // 레스토랑 ID를 사용하여 엔티티 생성
                .date(date)
                .time(time)
                .person(person)
                .build();
    }

}