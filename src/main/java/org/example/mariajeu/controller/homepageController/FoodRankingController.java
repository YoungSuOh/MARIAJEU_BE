package org.example.mariajeu.controller.homepageController;

import org.example.mariajeu.domain.homepageDomain.WineType;
import org.example.mariajeu.dto.ErrorDTO;
import org.example.mariajeu.dto.homepageDto.FoodRankingDTO;
import org.example.mariajeu.service.homepageService.FoodRankingService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/food-ranking")
public class FoodRankingController {

    private final FoodRankingService foodRankingService;

    public FoodRankingController(FoodRankingService foodRankingService) {
        this.foodRankingService = foodRankingService;
    }


    @GetMapping("/views")
    public ResponseEntity<?> getFoodByViewsRanking(@PageableDefault(size = 10) Pageable pageable) {
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByViewsRanking(pageable);
        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getFoodByCommentsRanking(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByLikesRanking(pageable);

        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @GetMapping("/likes")
    public ResponseEntity<?> getFoodByLikesRanking(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByCommentsRanking(pageable);

        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    //와인 타입에 따라 검색

    @GetMapping("/views/wine")
    public ResponseEntity<?> getFoodByViewsRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes,
                                                             @PageableDefault(page = 0, size = 10) Pageable pageable) {
        validateWineTypes(wineTypes);
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByViewsRankingByWineType(wineTypes, pageable);
        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @GetMapping("/comments/wine")
    public ResponseEntity<?> getFoodByCommentsRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes,
                                                                @PageableDefault(page = 0, size = 10) Pageable pageable) {
        validateWineTypes(wineTypes);
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByViewsRankingByWineType(wineTypes, pageable);
        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @GetMapping("/likes/wine")
    public ResponseEntity<?> getFoodByLikesRankingByWineType(@RequestParam(required = false) List<WineType> wineTypes,
                                                             @PageableDefault(page = 0, size = 10) Pageable pageable) {
        validateWineTypes(wineTypes);
        List<FoodRankingDTO> foodList = foodRankingService.getFoodByViewsRankingByWineType(wineTypes, pageable);
        if (foodList.isEmpty()) {
            String ErrorMessage = "No data";
            return ResponseEntity.ok(Collections.singletonList(ErrorMessage));
        } else {
            return ResponseEntity.ok(foodList);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "유효하지 않은 와인 타입";
        ErrorDTO errorDTO = ErrorDTO.builder()
                .errorStatus("INVALID_INPUT")
                .errorContent(errorMessage)
                .data(null)
                .build();
        return ResponseEntity.badRequest().body(errorDTO);
    }

    private void validateWineTypes(List<WineType> wineTypes) {
        if (wineTypes == null || wineTypes.isEmpty()) {
            throw new IllegalArgumentException("와인 타입을 입력하세요");
        }
    }


}
