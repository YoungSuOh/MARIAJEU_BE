package org.example.mariajeu.repository;

import org.example.mariajeu.domain.Menu;
import org.example.mariajeu.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
