package org.example.mariajeu.repository.restaurantRepository;

import org.example.mariajeu.domain.restaurantDomain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
