package org.example.mariajeu.service.homepageService;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.WineType;
import org.example.mariajeu.dto.homepageDto.FoodRankingDTO;
import org.example.mariajeu.repository.homepageRepository.FoodRankingRepository;
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

    //와인 타입

    public List<FoodRankingDTO> getFoodByViewsRankingByWineType(List<WineType> wineTypes) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByViewsRankingByWineType(wineTypes);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByCommentsRankingByWineType(List<WineType> wineTypes) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByCommentsRankingByWineType(wineTypes);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByLikesRankingByWineType(List<WineType> wineTypes) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByLikesRankingByWineType(wineTypes);
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

