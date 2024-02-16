package org.example.mariajeu.dto.restaurantDto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Menu;

@Getter
@NoArgsConstructor
public class MenuDto {
    private Long restaurantId;
    private String name;
    private Long price;
    private String menuImg; // S3 사용해서 URL로?

    @Builder
    public MenuDto(Long restaurantId, String name, Long price, String menuImg) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.menuImg = menuImg;
    }

    // dto -> entity

    // entity -> dto
    public static MenuDto fromEntity(Menu menu) {
        return MenuDto.builder()
                .restaurantId(menu.getRestaurant().getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .menuImg(menu.getMenuImg())
                .build();
    }
}


