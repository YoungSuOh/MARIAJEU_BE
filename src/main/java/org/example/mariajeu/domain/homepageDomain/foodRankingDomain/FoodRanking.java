package org.example.mariajeu.domain.homepageDomain.foodRankingDomain;

import jakarta.persistence.*;
import org.example.mariajeu.domain.homepageDomain.Food;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.Wine;

@Entity
public class FoodRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ranking;

    private int likes;

    private int views;

    private int comments;

    @ManyToOne
    @JoinColumn(name = "food_article_id")
    private FoodArticle foodArticle;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private Wine wine;


}
