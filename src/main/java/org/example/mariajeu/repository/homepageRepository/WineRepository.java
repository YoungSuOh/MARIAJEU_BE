package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
