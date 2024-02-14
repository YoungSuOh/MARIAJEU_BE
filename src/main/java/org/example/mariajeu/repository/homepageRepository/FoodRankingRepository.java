package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.WineType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRankingRepository extends JpaRepository<FoodArticle, Long> {
    @Query("SELECT fa FROM FoodArticle fa ORDER BY fa.views DESC")
    List<FoodArticle> findFoodArticlesByViewsRanking(Pageable pageable);

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.comments c GROUP BY fa.id ORDER BY COUNT(c) DESC")
    List<FoodArticle> findFoodArticlesByCommentsRanking(Pageable pageable);

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.likes l GROUP BY fa.id ORDER BY COUNT(l) DESC")
    List<FoodArticle> findFoodArticlesByLikesRanking(Pageable pageable);

    //와인타입에 따른 랭킹 조회
    @Query("SELECT fa FROM FoodArticle fa WHERE fa.wine.type IN :wineTypes ORDER BY fa.views DESC")
    List<FoodArticle> findFoodArticlesByViewsRankingByWineType(@Param("wineTypes") List<WineType> wineTypes,Pageable pageable);

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.comments c WHERE fa.wine.type IN :wineTypes GROUP BY fa.id ORDER BY COUNT(c) DESC")
    List<FoodArticle> findFoodArticlesByCommentsRankingByWineType(@Param("wineTypes") List<WineType> wineTypes,Pageable pageable);

    @Query("SELECT fa FROM FoodArticle fa LEFT JOIN fa.likes l WHERE fa.wine.type IN :wineTypes GROUP BY fa.id ORDER BY COUNT(l) DESC")
    List<FoodArticle> findFoodArticlesByLikesRankingByWineType(@Param("wineTypes") List<WineType> wineTypes,Pageable pageable);


}
