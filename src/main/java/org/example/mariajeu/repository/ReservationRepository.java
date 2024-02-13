package org.example.mariajeu.repository;

import org.example.mariajeu.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 레스토랑의 예약 목록 조회
    List<Reservation> findByRestaurantId(Long restaurantId);

    List<Reservation> findByDateAndTime(LocalDate date, LocalTime time);
}