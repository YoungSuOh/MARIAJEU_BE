package org.example.mariajeu.repository;

import org.example.mariajeu.domain.FoodArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodArticleRepository extends JpaRepository<FoodArticle, Long> {
}
