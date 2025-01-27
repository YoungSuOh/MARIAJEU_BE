package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticleLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodArticleLikesRepository extends JpaRepository<FoodArticleLikes,Long> {
    List<FoodArticleLikes> findByFoodArticleId(Long foodArticleId);
}
