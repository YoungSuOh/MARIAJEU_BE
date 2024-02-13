package org.example.mariajeu.controller;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.dto.reservation.ReservationRequestDto;
import org.example.mariajeu.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // 레스토랑 예약
    @PostMapping("/restaurants/reservation")
    public Long save(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.createReservation(reservationRequestDto);
    }

    // 레스토랑 예약 취소
    @DeleteMapping("/restaurants/reservation/{reservationId}")
    public void delete(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }



}
