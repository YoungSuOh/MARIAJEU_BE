package org.example.mariajeu.repository.HomePage;

import org.example.mariajeu.domain.HomePage.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}