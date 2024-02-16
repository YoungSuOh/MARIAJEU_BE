package org.example.mariajeu.controller.boardController;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.dto.boardDto.BoardRequestDTO;
import org.example.mariajeu.dto.boardDto.BoardResponseDTO;
import org.example.mariajeu.service.boardService.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody BoardRequestDTO boardRequestDTO) {

        boardService.save(boardRequestDTO);

        return new ResponseEntity<>("작성 완료", HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDTO> selectBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.selectBoard(boardId));
    }


}
