package org.example.mariajeu.dto.restaurant;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.example.mariajeu.domain.Restaurant;
import org.example.mariajeu.dto.menu.MenuDto;
import org.example.mariajeu.dto.reservation.ReservationDto;
import org.example.mariajeu.dto.review.ReviewDto;
import org.example.mariajeu.dto.review.ReviewResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Data
public class RestaurantResponseDto { // 식당 메인 메뉴 페이지 response
    private final Long id;
    private final String name;
    private final String address;
    private final Long corkage;
    private final String averagePrice;
    private final String info;

    private final List<ReservationDto> reservations;
    private final List<MenuDto> menus;
    private final List<ReviewResponseDto> reviews;
    private final String restaurantImg;

    // 생성자
    @Builder
    public RestaurantResponseDto(Long id, String name, String address, Long corkage, String averagePrice, String info, List<ReservationDto> reservations, List<MenuDto> menus, List<ReviewResponseDto> reviews, String restaurantImg) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.corkage = corkage;
        this.averagePrice = averagePrice;
        this.info = info;
        this.reservations = reservations;
        this.menus = menus;
        this.reviews = reviews;
        this.restaurantImg = restaurantImg;
    }

    // 엔티티에서 DTO로 변환하는 메서드
    public static RestaurantResponseDto fromEntity(Restaurant restaurant) {
        List<ReservationDto> reservationDtos = restaurant.getReservationList().stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());

        List<MenuDto> menuDtos = restaurant.getMenuList().stream()
                .map(MenuDto::fromEntity)
                .collect(Collectors.toList());
        List<ReviewResponseDto> reviewResponseDtos = restaurant.getReviewList().stream()
                .map(ReviewResponseDto::fromEntity)
                .collect(Collectors.toList());
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .corkage(restaurant.getCorkage())
                .averagePrice(restaurant.getAveragePrice())
                .info(restaurant.getInfo())
                .reservations(reservationDtos)
                .menus(menuDtos)
                .restaurantImg(restaurant.getRestaurantImg())
                .reviews(reviewResponseDtos)
                .build();
    }
}
