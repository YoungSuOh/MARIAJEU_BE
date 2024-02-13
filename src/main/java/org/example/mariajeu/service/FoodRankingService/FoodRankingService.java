package org.example.mariajeu.service.FoodRankingService;

import org.example.mariajeu.domain.FoodArticle.FoodArticle;
import org.example.mariajeu.dto.FoodRankingDTO.FoodRankingDTO;
import org.example.mariajeu.repository.FoodRankingRepository.FoodRankingRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodRankingService {

    private final FoodRankingRepository foodRankingRepository;

    public FoodRankingService(FoodRankingRepository foodRankingRepository) {
        this.foodRankingRepository=foodRankingRepository;
    }

    public List<FoodRankingDTO> getFoodByViewsRanking() {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByViewsRanking();
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByCommentsRanking() {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByCommentsRanking();
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByLikesRanking() {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByLikesRanking();
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FoodRankingDTO convertToDto(FoodArticle foodArticle) {
        return new FoodRankingDTO(
                foodArticle.getId(),
                foodArticle.getFood().getName(),
                foodArticle.getWine().getName(),
                foodArticle.getViews(),
                foodArticle.getComments().size(),
                foodArticle.getLikes().size()
        );
    }


}
