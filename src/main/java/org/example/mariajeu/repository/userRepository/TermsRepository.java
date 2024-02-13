package org.example.mariajeu.repository.userRepository;

import org.example.mariajeu.domain.userDomain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Long> {
    void deleteByUserName(String nickName);

}
