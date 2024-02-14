package org.example.mariajeu.repository.restaurantRepository;

import org.example.mariajeu.domain.restaurantDomain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 레스토랑 이름으로 검색
    List<Restaurant> findByName(String name);

    // 날짜, 시간, 인원 수로 검색


}
