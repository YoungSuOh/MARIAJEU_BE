package org.example.mariajeu.repository.boardRepository;

import org.example.mariajeu.domain.boardDomain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
