package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}