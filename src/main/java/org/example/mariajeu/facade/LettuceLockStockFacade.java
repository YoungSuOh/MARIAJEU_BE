package org.example.mariajeu.facade;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.dto.reservation.ReservationRequestDto;
import org.example.mariajeu.repository.RedisLockRepository;
import org.example.mariajeu.service.ReservationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade  {

    private final RedisLockRepository redisLockRepository;
    private final ReservationService reservationService;

    public void create(Long key, ReservationRequestDto reservationRequestDto) throws InterruptedException {
        // Lock 획득 시도
        while (!redisLockRepository.lock(key)) {
            //SpinLock 방식이 redis 에게 주는 부하를 줄여주기위한 sleep
            Thread.sleep(100);
        }

        //lock 획득 성공시
        try{
            reservationService.createReservation(reservationRequestDto);
        }finally {
            //락 해제
            redisLockRepository.unlock(key);
        }
    }
}