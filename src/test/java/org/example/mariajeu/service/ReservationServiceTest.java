package org.example.mariajeu.service;

import lombok.extern.slf4j.Slf4j;
import org.example.mariajeu.domain.Reservation;
import org.example.mariajeu.dto.reservation.ReservationRequestDto;
import org.example.mariajeu.dto.restaurant.RestaurantRequestDto;
import org.example.mariajeu.facade.LettuceLockStockFacade;
import org.example.mariajeu.repository.ReservationRepository;
import org.example.mariajeu.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    LettuceLockStockFacade lettuceLockStockFacade;

    @BeforeEach
    void setUp() {
        // 테스트용 레스토랑 정보 저장
        RestaurantRequestDto restaurantRequestDto = RestaurantRequestDto.builder()
                .name("Test Restaurant")
                .address("Test Address")
                .info("Test Info")
                .build();

        // 가짜 이미지 파일 생성
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "restaurantImg",                // 파일 이름
                "mock1.png",                    // 파일 원본 이름
                "image/png",                    // 파일 타입
                "mock content".getBytes()       // 파일 내용
        );
        restaurantService.registerRestaurant(restaurantRequestDto, mockMultipartFile);
    }

    // 동시에 여러개 요청 테스트 - 다른 시간
    @Test
    void concurrentCreateReservation() throws Exception {
        int numThreads = 500; // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            // CompletableFuture로 각 스레드에서 비동기적으로 예약 생성 작업 실행
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                try {
                    // 예약 생성
                    LocalDate date = LocalDate.of(2024, 3, 11);
                    LocalTime time = LocalTime.of(0, 0).plusMinutes(1* finalI);

                    Long reservationId = reservationService.createReservation(createReservationRequest(date, time));
                    // 생성된 예약이 올바르게 저장되었는지 확인
                    assertTrue(reservationRepository.existsById(reservationId));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, executorService);
        }

        // 모든 작업이 완료될 때까지 대기
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        //
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            log.info("Reservation ID: {}", reservation.getId());
            log.info("Restaurant ID: {}", reservation.getRestaurant().getId());
            log.info("Date: {}", reservation.getDate());
            log.info("Time: {}", reservation.getTime());
            log.info("Person: {}", reservation.getPerson());
            log.info("--------------------------------------");
        }
    }

    // 동시에 여러개 요청 테스트 - 같은 시간
    @Test
    void concurrentCreateSameTimeReservation() throws Exception {
        int numThreads = 2; // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        LocalDate date = LocalDate.of(2024, 3, 11);
        LocalTime time = LocalTime.of(12, 0); // 동일한 시간으로 설정
        for (int i = 0; i < numThreads; i++) {
            // CompletableFuture로 각 스레드에서 비동기적으로 예약 생성 작업 실행
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                try {
                    // 예약 생성
//                    Long reservationId = reservationService.createReservation(createReservationRequest(date, time));
                    lettuceLockStockFacade.create(1L, createReservationRequest(date, time));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, executorService);
        }

        // 모든 작업이 완료될 때까지 대기
        // 생성된 예약이 하나여야 함
        List<Reservation> reservations = reservationRepository.findByDateAndTime(date, time);
        assertFalse(reservations.size() > 1, "Multiple reservations created for the same time");

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        //
        List<Reservation> reservationList = reservationRepository.findAll();
        for (Reservation reservation : reservationList) {
            log.info("Reservation ID: {}", reservation.getId());
            log.info("Restaurant ID: {}", reservation.getRestaurant().getId());
            log.info("Date: {}", reservation.getDate());
            log.info("Time: {}", reservation.getTime());
            log.info("Person: {}", reservation.getPerson());
            log.info("--------------------------------------");
        }
    }

    // 다른 타이밍에 같은 시간 예약 테스트
    @Test
    void 중복_예약_테스트() throws Exception{
        LocalDate date = LocalDate.of(2024, 3, 11);
        LocalTime time = LocalTime.of(12, 0); // 동일한 시간으로 설정

        // 첫 번째 예약 생성
        Long firstReservationId = reservationService.createReservation(createReservationRequest(date, time));
        assertTrue(reservationRepository.existsById(firstReservationId));

        // 조금 시간이 지난 후 두 번째 예약 생성
        Thread.sleep(2000); // 10초 대기 (첫 번째 예약 후 시간 지연)

        try {
            Long secondReservationId = reservationService.createReservation(createReservationRequest(date, time));
            assertFalse(reservationRepository.existsById(secondReservationId), "Duplicate reservation created");
        } catch (IllegalArgumentException e) {
            // 예상대로 예외가 발생한 경우 테스트 성공
            assertTrue(true);
        }




        List<Reservation> reservationList = reservationRepository.findAll();
        for (Reservation reservation : reservationList) {
            log.info("Reservation ID: {}", reservation.getId());
            log.info("Restaurant ID: {}", reservation.getRestaurant().getId());
            log.info("Date: {}", reservation.getDate());
            log.info("Time: {}", reservation.getTime());
            log.info("Person: {}", reservation.getPerson());
            log.info("--------------------------------------");
        }
    }



    // 테스트를 위한 예약 요청 생성 메서드
    public ReservationRequestDto createReservationRequest(LocalDate date, LocalTime time) {
        Long person = 2L;
        // 레스토랑 ID를 임의로 설정하거나 실제 데이터베이스에서 사용할 수 있는 ID로 설정
        Long restaurantId = 1L;

        return ReservationRequestDto.builder()
                .restaurantId(restaurantId)
                .date(date)
                .time(time)
                .person(person)
                .build();
    }

}