package org.example.mariajeu.domain.restaurantDomain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.dto.restaurantDto.restaurant.RestaurantRequestDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", unique = true, nullable = false)
    private Long id; //레스토랑 ID

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>(); // 메뉴 리스트

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>(); // 예약 리스트

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false)
    private String name; //가게 이름

    @Column(nullable = false)
    private String address; //가게 주소

    private Long corkage; //콜키지 가격

    private String averagePrice; // 평균 가격 -> 사장이 입력?

    private String restaurantImg;

    @Column(nullable = false)
    private String info; // 매장 소개



//    private Boolean heart; // 좋아요(찜 여부)

    @Builder
    public Restaurant(RestaurantRequestDto restaurantRequestDto, String restaurantImg) {
        this.id = restaurantRequestDto.getId();
        this.menuList = restaurantRequestDto.getMenuList();
        this.reservationList = restaurantRequestDto.getReservationList();
        this.name = restaurantRequestDto.getName();
        this.address = restaurantRequestDto.getAddress();
        this.corkage = restaurantRequestDto.getCorkage();
        this.averagePrice = restaurantRequestDto.getAveragePrice();
        this.info = restaurantRequestDto.getInfo();
        this.restaurantImg = restaurantImg;
    }

    public void update(String address, String info, Long corkage) {
        this.address = address;
        this.info = info;
        this.corkage = corkage;
    }
}
