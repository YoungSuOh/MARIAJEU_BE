package org.example.mariajeu.repository;

import org.example.mariajeu.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

//    @Test
//    public void testFindAll() {
//        // 레스토랑 추가
//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setName("맛있는 식당1");
//        restaurantRepository.save(restaurant1);
//
//        Restaurant restaurant2 = new Restaurant();
//        restaurant2.setName("맛있는 식당2");
//        restaurantRepository.save(restaurant2);
//
//        // 모든 레스토랑 조회
//        List<Restaurant> allRestaurants = restaurantRepository.findAll();
//
//        // 레스토랑 리스트의 크기가 2여야 함
//        assertEquals(2, allRestaurants.size());
//    }
//
//    @Test
//    public void testFindByName() {
//        // 레스토랑 추가
//        Restaurant restaurant1 = new Restaurant();
//        restaurant1.setName("맛있는 식당1");
//        restaurantRepository.save(restaurant1);
//
//        Restaurant restaurant2 = new Restaurant();
//        restaurant2.setName("맛있는 식당2");
//        restaurantRepository.save(restaurant2);
//
//        // 이름으로 레스토랑 조회
//        List<Restaurant> foundRestaurants = restaurantRepository.findByName("맛있는 식당1");
//
//        // 맛있는 식당1이 조회되어야 함
//        assertEquals(1, foundRestaurants.size());
//        assertEquals("맛있는 식당1", foundRestaurants.get(0).getName());
//    }
}