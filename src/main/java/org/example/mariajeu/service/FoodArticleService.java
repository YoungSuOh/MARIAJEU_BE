package org.example.mariajeu.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.Food;
import org.example.mariajeu.domain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.FoodArticle.FoodArticleLikes;
import org.example.mariajeu.domain.Wine;
import org.example.mariajeu.domain.WineType;
import org.example.mariajeu.dto.FoodArticleDTO;
import org.example.mariajeu.repository.FoodArticleLikesRepository;
import org.example.mariajeu.repository.FoodArticleRepository;
import org.example.mariajeu.repository.FoodRepository;
import org.example.mariajeu.repository.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class FoodArticleService {

    private final FoodArticleRepository foodArticleRepository;
    private final FoodRepository foodRepository;
    private final WineRepository wineRepository;
    private final FoodArticleLikesRepository foodArticleLikesRepository;

    public List<FoodArticleDTO> getFoodArticlesByWineCharacteristics(WineType wineType, int boldness, int acidity, int fizziness, int tannic) {
        List<FoodArticle> foodArticles = foodArticleRepository.findByWineCharacteristics(wineType, boldness, acidity, fizziness, tannic);
        return foodArticles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    // FoodArticle 리스트를 FoodArticleDTO 리스트로 변환
    public List<FoodArticleDTO> getAllFoodArticles() {
        return foodArticleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // FoodArticle 엔티티를 FoodArticleDTO로 변환
    private FoodArticleDTO convertToDto(FoodArticle foodArticle) {
        return new FoodArticleDTO(
                foodArticle.getId(),
                foodArticle.getFood().getName(),
                foodArticle.getFood().getEnglishName(),
                foodArticle.getFood().getPhotoUrl(),
                foodArticle.getFood().getVideoUrl(),
                foodArticle.getFood().getPrimaryIngredients(),
                foodArticle.getFood().getSauceIngredients(),
                foodArticle.getFood().getInstructions(),
                foodArticle.getWine().getName(),
                foodArticle.getWine().getType(),
                foodArticle.getWine().getBoldness(),
                foodArticle.getWine().getAcidity(),
                foodArticle.getWine().getFizziness(),
                foodArticle.getWine().getTannic(),
                foodArticle.getLikes().size() // 좋아요 수
        );
    }

    public FoodArticleDTO addArticle(FoodArticleDTO foodArticleDTO) {
        // DTO를 엔티티로 변환
        FoodArticle foodArticle = foodArticleDTO.toEntity();

        // Food와 Wine 엔티티 저장
        Food savedFood = foodRepository.save(foodArticle.getFood());
        Wine savedWine = wineRepository.save(foodArticle.getWine());

        // 저장된 Food와 Wine으로 FoodArticle 업데이트
        foodArticle.setFood(savedFood);
        foodArticle.setWine(savedWine);

        // FoodArticle 저장
        FoodArticle savedFoodArticle = foodArticleRepository.save(foodArticle);

        // 저장된 엔티티를 다시 DTO로 변환하여 반환
        return new FoodArticleDTO(savedFoodArticle);
    }

    public FoodArticleDTO updateArticle(Long id, FoodArticleDTO foodArticleDTO) {
        // ID로 FoodArticle 찾기
        FoodArticle foodArticle = foodArticleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FoodArticle not found with id: " + id));

        // FoodArticle 엔티티 업데이트
        foodArticle.getFood().setName(foodArticleDTO.getFoodName());
        foodArticle.getFood().setEnglishName(foodArticleDTO.getFoodEnglishName());
        // 나머지 Food 필드 업데이트...
        foodArticle.getWine().setName(foodArticleDTO.getWineName());
        // 나머지 Wine 필드 업데이트...

        // 엔티티 저장
        FoodArticle updatedFoodArticle = foodArticleRepository.save(foodArticle);
        return new FoodArticleDTO(updatedFoodArticle);
    }

    public void deleteArticle(Long foodArticleId) {
        // FoodArticleLikes 레코드 먼저 삭제
        List<FoodArticleLikes> likes = foodArticleLikesRepository.findByFoodArticleId(foodArticleId);
        foodArticleLikesRepository.deleteAll(likes);

        // 이제 FoodArticle 삭제
        foodArticleRepository.deleteById(foodArticleId);
    }
}
