package org.example.mariajeu.dto.restaurantDto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Reservation;
import org.example.mariajeu.domain.restaurantDomain.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
//@Data // -> 무한 참조 주의
public class ReservationDto {
    private Long restaurantId; // 레스토랑 ID만을 참조하도록 수정
    private LocalDate date;
    private LocalTime time;
    private Long person;
    // User 관련 추가

    @Builder
    public ReservationDto(Long restaurantId, LocalDate date, LocalTime time, Long person) {
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
        this.person = person;
    }

    // dto -> entity
    public Reservation toEntity() {
        return Reservation.builder()
                .restaurant(Restaurant.builder().build()) // 레스토랑 ID를 사용하여 엔티티 생성
                .date(date)
                .time(time)
                .person(person)
                .build();
    }

    // entity -> dto
    public static ReservationDto fromEntity(Reservation reservation) {
        return ReservationDto.builder()
                .restaurantId(reservation.getRestaurant().getId())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .person(reservation.getPerson())
                .build();
    }

}