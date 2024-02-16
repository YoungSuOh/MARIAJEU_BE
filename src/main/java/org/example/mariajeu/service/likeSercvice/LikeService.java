package org.example.mariajeu.service.likeSercvice;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.boardDomain.Board;
import org.example.mariajeu.domain.boardDomain.Likes;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.repository.boardRepository.BoardRepository;
import org.example.mariajeu.repository.boardRepository.LikeRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final BoardRepository boardRepository;
    private final LikeRepository llikeRepository;
    private final UserRepository userRepository;

    @Transactional
    public String like(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        Optional<Likes> findLike = llikeRepository.findByBoardIdAndUserId(board, user);

        if (findLike.isEmpty()) {
            Likes likes = Likes.builder()
                    .board(board)
                    .user(user)
                    .build();
            llikeRepository.save(likes);
            return "좋아요";
        } else {
            llikeRepository.delete(findLike.get());
            return "좋아요 취소";
        }
    }
}