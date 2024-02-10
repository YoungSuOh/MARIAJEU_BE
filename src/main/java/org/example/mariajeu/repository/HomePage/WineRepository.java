package org.example.mariajeu.repository.HomePage;

import org.example.mariajeu.domain.HomePage.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
