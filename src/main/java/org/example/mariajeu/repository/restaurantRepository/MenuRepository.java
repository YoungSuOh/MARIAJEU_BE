package org.example.mariajeu.repository.restaurantRepository;

import org.example.mariajeu.domain.restaurantDomain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 레스토랑 이름으로 검색

    // 날짜, 시간, 인원 수로 검색


}
