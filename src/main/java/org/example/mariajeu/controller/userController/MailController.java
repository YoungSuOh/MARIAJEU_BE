package org.example.mariajeu.controller.userController;

import org.example.mariajeu.dto.ResponseDTO;
import org.example.mariajeu.dto.userDto.EmailCheckDTO;
import org.example.mariajeu.dto.userDto.EmailRequest;
import org.example.mariajeu.exception.AppException;
import org.example.mariajeu.exception.ErrorCode;
import org.example.mariajeu.service.mailService.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/Send")
    public ResponseEntity<ResponseDTO> mailSend(@RequestBody @Valid EmailRequest dto) {
        return ResponseEntity.ok(ResponseDTO.builder()
                .status(HttpStatus.OK)
                .message(mailService.joinEmail(dto.getEmail()))
                .build()
        );
    }

    @PostMapping("/AuthCheck")
    public ResponseEntity<ResponseDTO> AuthCheck(@RequestBody @Valid EmailCheckDTO dto){
        boolean Checked=mailService.CheckAuthNum(dto.getEmail(),dto.getAuthNum());
        if(Checked){
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status(HttpStatus.OK)
                    .message("인증이 완료되었습니다")
                    .build()
            );
        }
        else{
            throw new AppException(ErrorCode.BAD_REQUEST,"인증 번호가 맞지 않습니다.");
        }
    }

}