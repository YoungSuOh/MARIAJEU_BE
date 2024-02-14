package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRankingRepository extends JpaRepository<FoodArticle, Long> {
    @Query("SELECT fa FROM FoodArticle fa ORDER BY fa.views DESC")
    List<FoodArticle> findFoodArticlesByViewsRanking();

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.comments c GROUP BY fa.id ORDER BY COUNT(c) DESC")
    List<FoodArticle> findFoodArticlesByCommentsRanking();

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.likes l GROUP BY fa.id ORDER BY COUNT(l) DESC")
    List<FoodArticle> findFoodArticlesByLikesRanking();
}
