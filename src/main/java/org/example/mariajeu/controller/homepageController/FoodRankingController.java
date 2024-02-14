package org.example.mariajeu.controller.homepageController;

import org.example.mariajeu.domain.homepageDomain.WineType;
import org.example.mariajeu.dto.homepageDto.FoodRankingDTO;
import org.example.mariajeu.service.homepageService.FoodRankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food-ranking")
public class FoodRankingController {

    private final FoodRankingService foodRankingService;

    public FoodRankingController(FoodRankingService foodRankingService) {
        this.foodRankingService = foodRankingService;
    }

    @GetMapping("/views")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByViewsRanking() {
        return ResponseEntity.ok(foodRankingService.getFoodByViewsRanking());
    }

    @GetMapping("/comments")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByCommentsRanking() {
        return ResponseEntity.ok(foodRankingService.getFoodByCommentsRanking());
    }

    @GetMapping("/likes")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByLikesRanking() {
        return ResponseEntity.ok(foodRankingService.getFoodByLikesRanking());
    }

    //와인 타입에 따라 검색

    @GetMapping("/views/wine")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByViewsRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes) {
        return ResponseEntity.ok(foodRankingService.getFoodByViewsRankingByWineType(wineTypes));
    }


    @GetMapping("/comments/wine")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByCommentsRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes) {
        return ResponseEntity.ok(foodRankingService.getFoodByCommentsRankingByWineType(wineTypes));
    }

    @GetMapping("/likes/wine")
    public ResponseEntity<List<FoodRankingDTO>> getFoodByLikesRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes) {
        return ResponseEntity.ok(foodRankingService.getFoodByLikesRankingByWineType(wineTypes));
    }

    //예외처리


}
