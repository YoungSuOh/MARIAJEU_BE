package org.example.mariajeu.controller;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.WineType;
import org.example.mariajeu.dto.FoodArticleDTO;
import org.example.mariajeu.service.FoodArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/FoodArticle")
public class FoodArticleController {

    private final FoodArticleService foodArticleService;

    // 모든 FoodArticle 데이터를 DTO 형태로 조회
    @GetMapping
    public List<FoodArticleDTO> getAllFoodArticles() {
        return foodArticleService.getAllFoodArticles();
    }

    // FoodArticle 추가
    @PostMapping
    public ResponseEntity<?> addArticle(@RequestBody FoodArticleDTO foodArticleDTO) {
        FoodArticleDTO savedArticleDTO = foodArticleService.addArticle(foodArticleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }

    // FoodArticle 수정
    @PutMapping("/{id}")    
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody FoodArticleDTO foodArticleDTO) {
        FoodArticleDTO updatedArticleDTO = foodArticleService.updateArticle(id, foodArticleDTO);
        return ResponseEntity.ok(updatedArticleDTO);
    }

    // FoodArticle 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        foodArticleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodArticleDTO>> getFoodArticlesByWineCharacteristics(
            @RequestParam WineType wineType,
            @RequestParam int boldness,
            @RequestParam int acidity,
            @RequestParam int fizziness,
            @RequestParam int tannic) {

        List<FoodArticleDTO> articles = foodArticleService.getFoodArticlesByWineCharacteristics(wineType, boldness, acidity, fizziness, tannic);
        return ResponseEntity.ok(articles);
    }
}

