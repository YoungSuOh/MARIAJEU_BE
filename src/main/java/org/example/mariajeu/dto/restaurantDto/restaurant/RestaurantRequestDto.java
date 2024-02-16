package org.example.mariajeu.dto.restaurantDto.restaurant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Menu;
import org.example.mariajeu.domain.restaurantDomain.Reservation;

import java.util.List;

@Getter
@NoArgsConstructor
//@Data // -> 무한 참조 주의
public class RestaurantRequestDto {
    private Long id;
    private List<Menu> menuList;
    private List<Reservation> reservationList;
    private String name;
    private String address;
    private Long corkage;
    private String averagePrice;
    private String info;
    // User 관련 추가

    @Builder
    public RestaurantRequestDto(Long id, List<Menu> menuList, List<Reservation> reservationList,
                                String name, String address, Long corkage, String averagePrice, String info, String restaurantImg) {
        this.id = id;
        this.menuList = menuList;
        this.reservationList = reservationList;
        this.name = name;
        this.address = address;
        this.corkage = corkage;
        this.averagePrice = averagePrice;
        this.info = info;
    }

    // dto -> entity
//    public Restaurant toEntity() {
//        return Restaurant.builder()
//                .id(id)
//                .name(name)
//                .address(address)
//                .corkage(corkage)
//                .averagePrice(averagePrice)
//                .info(info)
//                .restaurantImg(restaurantImg)
//                .build();
//    }

}