package org.example.mariajeu.dto.FoodRankingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.FoodArticle.FoodArticle;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRankingDTO {
    private Long id;
    private String foodName;
    private String wineName;
    private int views;
    private int comments;
    private int likes;

    public FoodRankingDTO(FoodArticle foodArticle, int views, int comments, int likes) {
        this.id = foodArticle.getId();
        this.foodName = foodArticle.getFood().getName();
        this.wineName = foodArticle.getWine().getName();
        this.views = views;
        this.comments = comments;
        this.likes = likes;
    }
}