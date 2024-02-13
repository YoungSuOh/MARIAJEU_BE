package org.example.mariajeu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mariajeu.domain.Restaurant;
import org.example.mariajeu.dto.menu.MenuDto;
import org.example.mariajeu.dto.restaurant.RestaurantRequestDto;
import org.example.mariajeu.dto.restaurant.RestaurantResponseDto;
import org.example.mariajeu.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final S3Upload s3Upload;

    @Transactional
    public Long registerRestaurant(RestaurantRequestDto restaurantRequestDto, MultipartFile restaurantImg) {
        String postImg = null;
        if (!restaurantImg.isEmpty()) {
            try {
                postImg = s3Upload.uploadFiles(restaurantImg, "restaurantImg");
                System.out.println(restaurantImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Restaurant restaurant = new Restaurant(restaurantRequestDto, postImg); // -> 이 부분 그냥 restaurantRequestDto에 postImg만 추가해서(set으로 말고) 어떻게 안되나?

        return restaurantRepository.save(restaurant).getId();
    }

    @Transactional
    public List<RestaurantResponseDto> getAllRestaurants() {
        List<Restaurant> restaurantsEntities = restaurantRepository.findAll();
        List<RestaurantResponseDto> restaurantResponseDtoList = new ArrayList<>();

        for (Restaurant restaurantEntity : restaurantsEntities) {
            RestaurantResponseDto restaurantResponseDto = RestaurantResponseDto.fromEntity(restaurantEntity);
            restaurantResponseDtoList.add(restaurantResponseDto);
        }

        return restaurantResponseDtoList;
    }

    // 레스토랑 상세정보
    @Transactional
    public RestaurantResponseDto getRestaurant(Long restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurantEntity = restaurantOptional.get();
            return RestaurantResponseDto.fromEntity(restaurantEntity);
        } else {

            // 레스토랑이 없을 경우에 대한 예외 처리
            throw new RestaurantNotFoundException("Restaurant with id " + restaurantId + " not found");
//            log.error("error log = {레스토랑이 없음}");
        }
    }

    // 레스토랑 삭제 (관리자)
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    // 레스토랑 정보 수정
    @Transactional
    public Long update(final Long restaurantId, final RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("레스토랑을 찾을 수 없습니다. ID: " + restaurantId));
        restaurant.update(restaurantRequestDto.getAddress(), restaurantRequestDto.getInfo(), restaurantRequestDto.getCorkage());
        return restaurantId;
    }




    // 필요에 따라 다른 서비스 메서드를 추가할 수 있습니다.
}