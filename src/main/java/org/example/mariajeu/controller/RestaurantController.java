package org.example.mariajeu.controller;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.dto.menu.MenuDto;
import org.example.mariajeu.dto.restaurant.RestaurantRequestDto;
import org.example.mariajeu.dto.restaurant.RestaurantResponseDto;
import org.example.mariajeu.dto.review.ReviewDto;
import org.example.mariajeu.service.MenuService;
import org.example.mariajeu.service.RestaurantNotFoundException;
import org.example.mariajeu.service.RestaurantService;
import org.example.mariajeu.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final ReviewService reviewService;

    // (관리자) 새로운 레스토랑
    @PostMapping
    public Long registerRestaurant(@RequestPart("data") RestaurantRequestDto restaurantRequestDto, @RequestPart(required = false) MultipartFile restaurantImg) {
        return restaurantService.registerRestaurant(restaurantRequestDto, restaurantImg);
    }

    // 전체 레스토랑 목록 불러오기
    @GetMapping
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // 레스토랑 상세 정보 불러오기
    @GetMapping("/{restaurantId}")
    public RestaurantResponseDto restaurantDetail(@PathVariable("restaurantId") Long restaurantId) throws RestaurantNotFoundException {
        return restaurantService.getRestaurant(restaurantId);
    }

    // (관리자) 레스토랑 삭제
    @DeleteMapping("/{restaurantId}")
    public void delete(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }


    // 레스토랑 내용 수정 (사장)
    @PatchMapping("/{restaurantId}")
    public Long save(@PathVariable final Long restaurantId, @RequestBody final RestaurantRequestDto restaurantRequestDto) {
        return restaurantService.update(restaurantId, restaurantRequestDto);
    }

    // 레스토랑 찜하기



    // 레스토랑 메뉴 추가(사장)
    @PostMapping("/{restaurantId}/menu")
    public Long registerMenu(@PathVariable final Long restaurantId, @RequestPart("data") MenuDto menuDto, @RequestPart(required = false) MultipartFile menuImg) {
        return menuService.registerMenu(menuDto, restaurantId, menuImg);
    }

    // 레스토랑 메뉴 삭제(사장)
    @DeleteMapping("/menu/{menuId}")
    public void deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
    }

    // 레스토랑 리뷰 작성
    @PostMapping("/{restaurantId}/review")
    public Long registerReview(@PathVariable final Long restaurantId, @RequestPart("data") ReviewDto reviewDto, @RequestPart(required = false) MultipartFile reviewImg) {
        return reviewService.registerReview(reviewDto, restaurantId, reviewImg);
    }

    @DeleteMapping("/{restaurantId}/review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId, @PathVariable String restaurantId) {
        reviewService.deleteReview(reviewId);
    }


}
