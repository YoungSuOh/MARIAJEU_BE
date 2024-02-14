package org.example.mariajeu.service.homepageService;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.WineType;
import org.example.mariajeu.dto.homepageDto.FoodRankingDTO;
import org.example.mariajeu.repository.homepageRepository.FoodRankingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodRankingService {

    private final FoodRankingRepository foodRankingRepository;


    public FoodRankingService(FoodRankingRepository foodRankingRepository) {
        this.foodRankingRepository=foodRankingRepository;
    }

    public List<FoodRankingDTO> getFoodByViewsRanking(Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByViewsRanking(pageable);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByCommentsRanking(Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByCommentsRanking(pageable);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByLikesRanking(Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByLikesRanking(pageable);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //와인 타입

    public List<FoodRankingDTO> getFoodByViewsRankingByWineType(List<WineType> wineTypes, Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByViewsRankingByWineType(wineTypes,pageable);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByCommentsRankingByWineType(List<WineType> wineTypes,Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByCommentsRankingByWineType(wineTypes,pageable);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FoodRankingDTO> getFoodByLikesRankingByWineType(List<WineType> wineTypes,Pageable pageable) {
        List<FoodArticle> foodArticles = foodRankingRepository.findFoodArticlesByLikesRankingByWineType(wineTypes,pageable);
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

