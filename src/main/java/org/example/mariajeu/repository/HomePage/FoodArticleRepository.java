package org.example.mariajeu.repository.HomePage;

import org.example.mariajeu.domain.HomePage.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.HomePage.WineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodArticleRepository extends JpaRepository<FoodArticle, Long> {

    // 와인의 타입과 특성에 맞는 FoodArticle 검색
    @Query("SELECT fa FROM FoodArticle fa WHERE fa.wine.type = :wineType AND fa.wine.boldness = :boldness AND fa.wine.acidity = :acidity AND fa.wine.fizziness = :fizziness AND fa.wine.tannic = :tannic")
    List<FoodArticle> findByWineCharacteristics(@Param("wineType") WineType wineType, @Param("boldness") int boldness, @Param("acidity") int acidity, @Param("fizziness") int fizziness, @Param("tannic") int tannic);
}
