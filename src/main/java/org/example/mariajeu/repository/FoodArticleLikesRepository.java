package org.example.mariajeu.repository;

import org.example.mariajeu.domain.FoodArticle.FoodArticleLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodArticleLikesRepository extends JpaRepository<FoodArticleLikes,Long> {
    List<FoodArticleLikes> findByFoodArticleId(Long foodArticleId);
}
