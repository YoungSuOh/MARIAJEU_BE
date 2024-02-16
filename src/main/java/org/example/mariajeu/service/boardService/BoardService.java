package org.example.mariajeu.service.boardService;

import lombok.RequiredArgsConstructor;

import org.example.mariajeu.domain.boardDomain.Board;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.boardDto.BoardRequestDTO;
import org.example.mariajeu.dto.boardDto.BoardResponseDTO;
import org.example.mariajeu.repository.boardRepository.BoardRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(BoardRequestDTO boardRequestDTO) {
        User user = userRepository.findById(boardRequestDTO.getUserId()).orElseThrow();

        Board board = getBoard(boardRequestDTO, user);

        boardRepository.save(board);
    }

    @Transactional
    public BoardResponseDTO selectBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        board.updateViewCount();

        User user = userRepository.findById(board.getUser().getId()).orElseThrow();
        return BoardResponseDTO.builder()
                .userNickname(user.getNickName())
                .foodName(board.getFoodName())
                .foodKind(board.getFoodKind())
                .ateDate(board.getAteDate())
                .ateLocation(board.getAteLocation())
                .withPerson(board.getWithPerson())
                .wineName(board.getWineName())
                .winery(board.getWinery())
                .createdYear(board.getCreatedYear())
                .kind(board.getKind())
                .country(board.getCountry())
                .createdLocation(board.getCreatedLocation())
                .content(board.getContent())
                .build();
    }




    private static Board getBoard(BoardRequestDTO boardRequestDTO, User findUser) {
        Board board = Board.builder()
                .foodName(boardRequestDTO.getFoodName())
                .foodKind(boardRequestDTO.getFoodKind())
                .ateDate(boardRequestDTO.getAteDate())
                .ateLocation(boardRequestDTO.getAteLocation())
                .withPerson(boardRequestDTO.getWithPerson())
                .wineName(boardRequestDTO.getWineName())
                .winery(boardRequestDTO.getWinery())
                .createdYear(boardRequestDTO.getCreatedYear())
                .kind(boardRequestDTO.getKind())
                .country(boardRequestDTO.getCountry())
                .createdLocation(boardRequestDTO.getCreatedLocation())
                .content(boardRequestDTO.getContent())
                .user(findUser)
                .build();
        return board;
    }


}
