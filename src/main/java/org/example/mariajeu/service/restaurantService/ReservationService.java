package org.example.mariajeu.service.restaurantService;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Reservation;
import org.example.mariajeu.domain.restaurantDomain.Restaurant;
import org.example.mariajeu.dto.restaurantDto.reservation.ReservationRequestDto;
import org.example.mariajeu.repository.restaurantRepository.ReservationRepository;
import org.example.mariajeu.repository.restaurantRepository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;

    // 예약 생성
    @Transactional
    public Long createReservation(ReservationRequestDto reservationRequestDTO) { // 매개변수로 User도 추가
        // 예약자 이름, 전화번호 User에서 받아서 추가

        // 예약 생성하려면 -> 1. 예약자 정보 받고, 2. 레스토랑 정보 받고, 3. 예약 정보 받고

        // 현재 날짜와 시간
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // 예약 날짜와 시간
        LocalDate reservationDate = reservationRequestDTO.getDate();
        LocalTime reservationTime = reservationRequestDTO.getTime();

        // 예약 시간과 현재 시간을 LocalDateTime으로 변환하여 비교
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, currentTime);
        LocalDateTime reservationDateTime = LocalDateTime.of(reservationDate, reservationTime);

        // 현재 시간보다 이전인지 확인
        if (reservationDateTime.isBefore(currentDateTime)) {
            throw new IllegalArgumentException("예약할 수 없는 날짜나 시간입니다.");
        }

        // 같은 시간에 이미 예약이 있는지 확인
        List<Reservation> existingReservations = reservationRepository.findByDateAndTime(reservationDate, reservationTime); // entity로 꺼내면 안되나?
        if (!existingReservations.isEmpty()) {
            // 이미 예약이 있는 경우
            throw new IllegalArgumentException("이미 해당 시간에 예약이 있습니다.");
        }

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(reservationRequestDTO.getRestaurantId());

        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return reservationRepository.save(reservationRequestDTO.toEntity(restaurant)).getId();
        } else {
            throw new IllegalArgumentException("해당 ID의 레스토랑을 찾을 수 없습니다: " + reservationRequestDTO.getRestaurantId());
        }


        // 예약 생성
    }

    // 예약 삭제
    @Transactional
    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }


}
