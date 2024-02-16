package org.example.mariajeu.service.restaurantService;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.restaurantDomain.Menu;
import org.example.mariajeu.domain.restaurantDomain.Restaurant;
import org.example.mariajeu.dto.restaurantDto.menu.MenuDto;
import org.example.mariajeu.repository.restaurantRepository.MenuRepository;
import org.example.mariajeu.repository.restaurantRepository.RestaurantRepository;
import org.example.mariajeu.service.S3Upload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final S3Upload s3Upload;


    // 레스토랑 메뉴 추가 (사장)
    @Transactional
    public Long registerMenu(MenuDto menuDto, Long restaurantId, MultipartFile menuImg) {
        String postImg = null;
        if (!menuImg.isEmpty()) {
            try {
                postImg = s3Upload.uploadFiles(menuImg, "menuImg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("레스토랑을 찾을 수 없습니다. ID: " + restaurantId));

        Menu menu = new Menu(menuDto, restaurant, postImg);

        return menuRepository.save(menu).getId();
    }

    // 레스토랑 메뉴 삭제 (사장)
    @Transactional
    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }
}
