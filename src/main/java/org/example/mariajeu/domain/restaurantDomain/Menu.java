package org.example.mariajeu.domain.restaurantDomain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mariajeu.dto.restaurantDto.menu.MenuDto;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    private Long id; //메뉴 ID

    private String name; // 메뉴 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Long price; // 메뉴 가격

    private String menuImg; // S3 사용해서 URL로?

    @Builder
    public Menu(MenuDto menuDto, Restaurant restaurant, String menuImg) {
        this.name = menuDto.getName();
        this.restaurant = restaurant;
        this.price = menuDto.getPrice();
        this.menuImg = menuImg;
    }

}
